Ext.define('Client',{
	extend: 'Ext.data.Model',

	fields: [
		{id: 'id', type: 'int'},
		{name: 'name', type: 'string'},
		{name: 'cpf', type: 'string'},
		{name: 'rg', type: 'string'},
		{name: 'filiation', type: 'string'},
		{name: 'phone', type: 'string'},
		{name: 'cellPhone', type: 'string'},
		{name: 'referencePhone', type: 'string'},
		{name: 'email', type: 'string'}
	],

	proxy: {
		type: 'rest',
		url: 'client',
		format: 'json'
	}
});