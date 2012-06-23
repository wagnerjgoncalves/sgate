package util;

import exceptions.ValidationException;
import play.data.validation.Validation;

public class ValidationUtil {

	public static void validate(Object model) {
		
		Validation validation = Validation.current();
		validation.clear();
		
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
	    	
	        throw new ValidationException(sb.toString());
		}
	}
}
