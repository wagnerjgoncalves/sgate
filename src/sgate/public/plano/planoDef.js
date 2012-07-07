Ext.define('Plano',{
  
    extend: 'Ext.data.Model',

  fields: [
    {name: 'id', type: 'int', useNull: true},
    {name: 'nome', type: 'string'},
    {name: 'descricao', type: 'string'},
    {name: 'preco', type: 'numeric'},
    {name: 'banda', type: 'string'},
    {name: 'tipo', type: 'object'}
  ],

  proxy: {
    type: 'rest',
    url: '/planos'
  }

});

var planosStore = Ext.create('Ext.data.JsonStore', {
    model: 'Plano',
    proxy: {
        type: 'ajax',
        url: '/planos',
        reader: {
            type: 'json',
            root: 'planos'
        }
    }
});

var getListPlanos  = function(updatePlano){

  var listView = Ext.create('Ext.grid.Panel', {
    width:600,
    height:250,
    collapsible:true,
    title:'Listagem',

    renderTo: document.body,

    store: planosStore,
    multiSelect: true,
    viewConfig: {
        emptyText: 'No plano to display'
    },

    columns: [
        {
        text: 'Identificador',
            flex: 50,
            dataIndex: 'id'
        },{
            text: 'Nome',
            flex: 50,
            dataIndex: 'nome'
        },
        {
            text: 'Descricao',
            flex: 50,
            dataIndex: 'descricao'
        },
        {
            text: 'Preco',
            flex: 50,
            dataIndex: 'preco'
        },
        {
            text: 'Banda',
            flex: 50,
            dataIndex: 'banda'
        },
         {
            text: 'Opções',
            xtype:'actioncolumn',
            width:50,
            items: [
            { 
                icon: 'http://www.townmountain.net/images/edit-button.jpg',
                tooltip: 'Editar',
                handler: function(grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex);
                    Plano.load( rec.get("id") , {
                                  success: function(p) {
                                     updatePlano(p);
                                 }
                    });
                }
            },
            {

                icon: 'http://www.ufscar.br/~ccet/novo/delete.gif',
                tooltip: 'Excluir',
                handler: function(grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex);
                    Plano.load( rec.get("id") , {
                                  success: function(p) {
                                     p.destroy();
                                     planosStore.load();
                                 }
                    });
                }
            }]
        }
    ]
  }); 

  return listView;

};

var newPlanoForm = function(plano, listView){
  plano = plano || Ext.create("Plano", {});
  var form = Ext.create('Ext.form.Panel', {
      title: 'Plano',
      bodyPadding: 5,
      width: 600,
      layout: 'anchor',
      
      defaults: {
          anchor: '100%'
      },

      defaultType: 'textfield',
      items: [
         {
            fieldLabel: 'Id',
            name: 'id',
            xtype: 'hiddenfield'
        },
        {
          fieldLabel: 'Nome',
          name: 'nome',
          allowBlank: false
      },
      {
          fieldLabel: 'Descricao',
          name: 'descricao',
          allowBlank: false
      },
      {
          fieldLabel: 'Preco',
          name: 'preco',
          allowBlank: false
      },
      {
          fieldLabel: 'Banda',
          name: "banda",
          allowBlank: false
      }
      ],

      buttons:[
      {
          text: 'Cancelar',
          handler: function(){
            form.getForm.reset();
          }

        },
        {
        text: 'Salvar',
          formBind: true, 
          disabled: true,
          handler: function() {
            form.getForm().updateRecord(plano);
            plano = Ext.create("Plano", plano.data);
                var tipo = {
                    id: 1,
                    nome: 'Internet'
                };

                plano.set('tipo', tipo);
                plano.save();

                form.setVisible(false);
                listView.setVisible(true);
                planosStore.load();
                form.getForm().reset();
          }
        }

      ],
      
      renderTo: Ext.getBody()
  });
  return form;
};
