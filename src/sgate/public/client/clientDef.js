;Ext.define('Cliente',{
	extend: 'Ext.data.Model',

	fields: [
		{id: 'id', type: 'int'},
		{name: 'nome', type: 'string'},
		{name: 'cpf', type: 'string'},
		{name: 'rg', type: 'string'},
		{name: 'filiacao', type: 'string'},
		{name: 'telreferencia', type: 'string'},
		{name: 'telfixo', type: 'string'},
		{name: 'telcelular', type: 'string'},
		{name: 'email', type: 'string'}
	],

	proxy: {
		type: 'rest',
		url: '/clientes'
	}
});