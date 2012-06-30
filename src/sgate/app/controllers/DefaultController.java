package controllers;

import play.mvc.Controller;

public class DefaultController extends Controller {

	protected static void returnIfNotValid(Object model) {
		
		validation.valid(model);
		
		if (validation.hasErrors()) {
			
	    	StringBuilder sb = new StringBuilder();
	    	int length = validation.errors().size();
	    	play.data.validation.Error error = null;
	    	
	    	for (int i = 0; i < length; i++) {
	    		
	    		error = validation.errors().get(i);
	    		sb.append(error.getKey() + ": " + error.message());
	    		
	    		if (i != length - 1) {
	    			sb.append(";");
	    		}
	    	}
	    	
	        error(sb.toString());
		}
	}
	
	protected static void returnIfNull(Object model, Object ... models) {
		
		if (model == null) {
			renderError("O id informado não foi encontrado.");
		}
		
		if (models != null && models.length > 0) {
		 
		    for (int i = 0; i < models.length; i++) {
		    
		        if (models[i] == null) {
		        	renderError("O id informado não foi encontrado.");
			        return;
		        }
		    }
		}
	}
	
	protected static void returnIfNotFound(Object model, Object ... models) {

        returnIfNull(model, models);
	}
	
    protected static void renderError(String ... messages) {
        
    	StringBuilder sb = new StringBuilder();
    	
    	for (int i = 0; i < messages.length; i++) {
    		
    		sb.append(messages[i]);
    		
    		if (i != messages.length - 1) {
    			sb.append(";");
    		}
    	}
    	
        error(sb.toString());
    }
	
}
