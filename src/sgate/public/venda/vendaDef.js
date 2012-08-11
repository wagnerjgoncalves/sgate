Ext.define('Venda',{
  
  extend: 'Ext.data.Model',

  fields: [
    {name: 'id', type: 'int', useNull: true},
    {name: 'nome', type: 'string'},
    {name: 'data', type: 'auto'},
    {name: 'valor', type: 'auto'},
    {name: 'desconto', type: 'auto'},
    {name: 'total', type: 'auto'}
  ],

  proxy: {
    type: 'rest',
    url: '/vendas'
  }

});


var vendasStore = Ext.create('Ext.data.JsonStore', {
    model: 'Venda',
    proxy: {
        type: 'ajax',
        url: '/vendas',
        reader: {
            type: 'json',
            root: 'vendas'
        }
    }
});

var getListVendas  = function(updateVenda){

  var listView = Ext.create('Ext.grid.Panel', {
    width:600,
    height:250,
    collapsible:true,
    title:'Listagem',

    renderTo: document.body,

    store: vendasStore,
    multiSelect: true,
    viewConfig: {
        emptyText: 'No venda to display'
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
          text: 'Data',
          xtype: 'datecolumn',
          format: 'm-d h:i a',
          flex: 35,
          dataIndex: 'data'
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
                    Venda.load( rec.get("id") , {
                            success: function(v) {
                               updateVenda(v);
                           }
                    });
                }
            },
            {

                icon: 'http://www.ufscar.br/~ccet/novo/delete.gif',
                tooltip: 'Excluir',
                handler: function(grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex);
                    Venda.load( rec.get("id") , {
                                  success: function(v) {
                                     v.destroy();
                                     vendasStore.load();
                                 }
                    });
                }
            }]
        }
    ]
  }); 

  return listView;

};

var newVendaForm = function(venda, listView){
  venda = venda || Ext.create("Venda", {});
  var form = Ext.create('Ext.form.Panel', {
      title: 'Venda',
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
          fieldLabel: 'Data',
          name: 'data',
          xtype: 'datefield'
        },
        {
          fieldLabel: 'Valor',
          name: 'valor',
          allowBlank: false,
          xtype: 'displayfield'
        },
        {
          fieldLabel: 'Desconto',
          name: 'desconto',
          allowBlank: false
        },
        {
          fieldLabel: 'Total',
          name: 'total',
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
            form.getForm().updateRecord(venda);
            venda = Ext.create("Venda", venda.data);                
                venda.save();
                form.setVisible(false);
                listView.setVisible(true);
                vendassStore.load();
                form.getForm().reset();
          }
        }

      ],
      
      renderTo: Ext.getBody()
  });
  return form;
};
