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

var getListPlanos  = function(){

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
        }
    ]
  }); 

  return listView;

};

var newPlanoForm = function(){
  var plano = Ext.create("Plano", {});
  var form = Ext.create('Ext.form.Panel', {
      title: 'Plano',
      bodyPadding: 5,
      width: 600,
      layout: 'anchor',
      
      defaults: {
          anchor: '100%'
      },

      defaultType: 'textfield',
      items: [{
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
                plano.set('id', null);
                plano.set('tipo', tipo);
            plano.save();
          }
        }

      ],
      
      renderTo: Ext.getBody()
  });
  return form;
};
