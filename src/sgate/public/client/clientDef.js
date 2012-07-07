Ext.define('Endereco',{
    
    extend: 'Ext.data.Model',

    fields: [
        {name: 'id', type: 'int'},
        {name: 'logradouro', type: 'string'},
        {name: 'numero', type: 'int'},
        {name: 'bairro', type: 'String'},
        {name: 'complemento', type: 'string'},
        {name: 'cidade', type: 'string'},
        {name: 'uf', type: 'string'},
        {name: 'cep', type: 'string'}
    ]

});

Ext.define('Cliente',{
	
    extend: 'Ext.data.Model',

	fields: [
		{name: 'id', type: 'int', useNull: true},
		{name: 'nome', type: 'string'},
		{name: 'cpf', type: 'string'},
		{name: 'rg', type: 'string'},
		{name: 'filiacao', type: 'string'},
		{name: 'telreferencia', type: 'string'},
		{name: 'telfixo', type: 'string'},
		{name: 'telcelular', type: 'string'},
		{name: 'email', type: 'string'},
        {name: 'endereco', type: 'object'}
	],

	proxy: {
		type: 'rest',
		url: '/clientes'
	}

});

var clientStore = Ext.create('Ext.data.JsonStore', {
    model: 'Cliente',
    proxy: {
        type: 'ajax',
        url: '/clientes',
        reader: {
            type: 'json',
            root: 'clients'
        }
    }
});

var getListClients  = function( updateCliente ){

	var listView = Ext.create('Ext.grid.Panel', {
    width:600,
    height:250,
    collapsible:true,
    title:'Listagem',

    renderTo: document.body,

    store: clientStore,
    multiSelect: true,
    viewConfig: {
        emptyText: 'No cliente to display'
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
            text: 'Cpf',
            flex: 50,
            dataIndex: 'cpf'
        },
        {
            text: 'Telefone Fixo',
            flex: 50,
            dataIndex: 'telfixo'
        },
        {
            text: 'Telefone Celular',
            flex: 50,
            dataIndex: 'telcelular'
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
                    Cliente.load( rec.get("id") , {
                                  success: function(c) {
                                     updateCliente(c);
                                 }
                    });
                }
            },{
                icon: 'http://www.ufscar.br/~ccet/novo/delete.gif',
                tooltip: 'Excluir',
                handler: function(grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex);
                    Cliente.load( rec.get("id") , {
                                  success: function(c) {
                                     c.destroy();
                                     clientStore.load();
                                 }
                    });
                }
            }]
        }
    ]
	});	

	return listView;

};

var newClientForm = function(cliente, listView){
	cliente = cliente || Ext.create("Cliente", {});
	var form = Ext.create('Ext.form.Panel', {
	    title: 'Cliente',
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
        	fieldLabel: 'CPF',
        	name: 'cpf',
        	allowBlank: false
    	},
    	{
        	fieldLabel: 'RG',
        	name: 'rg',
        	allowBlank: false
    	},
    	{
        	fieldLabel: 'Filiacao',
        	name: "filiacao",
        	allowBlank: false
    	},
    	{
        	fieldLabel: 'Telefone Fixo',
        	name: 'telfixo',
        	allowBlank: false
    	},
    	{
        	fieldLabel: 'Telefone Celular',
        	name: 'telcelular',
        	allowBlank: false
    	},
    	{
        	fieldLabel: 'Telefone Referencia',
        	name: 'telreferencia',
        	allowBlank: false
    	},
    	{
        	fieldLabel: 'Email',
        	name: 'email',
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
        		form.getForm().updateRecord(cliente);
        		client = Ext.create("Cliente", cliente.data);
                var endereco = {
                    id: null,
                    logradouro: 'Novo',
                    numero: 1,
                    bairro: 'Lavrinhas',
                    complemento: 'casa',
                    cidade: 'Lavras',
                    uf: 'mg',
                    cep: '37200000'
                };

                client.set('endereco', endereco);
        		client.save();

                form.setVisible(false);
                listView.setVisible(true);
                clientStore.load();
                form.getForm().reset();
        	}
        }

    	],
    	
    	renderTo: Ext.getBody()
	});
	return form;
};
