Ext.define('Client',{
	extend: 'Ext.data.Model',

	fields: [
		{id: 'id', type: 'int'},
		{name: 'name', type: 'string'},
		{name: 'cpf', type: 'string'},
		{name: 'rg', type: 'string'}
	],

	proxy: {
		type: 'rest',
		url: 'client',
		format: 'json'
	}
});