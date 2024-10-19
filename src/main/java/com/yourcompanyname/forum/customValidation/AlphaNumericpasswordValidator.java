package com.yourcompanyname.forum.customValidation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AlphaNumericpasswordValidator implements ConstraintValidator<AlphaNumericPassword, String> {
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub

		Boolean hasLowerCaseLetter = false;
		Boolean hasUpperCaseLetter = false;
		Boolean hasNumber = false;
		Boolean hasSpecialCharecter = false;

		if (StringUtils.hasText(value) && value.length() > 6) {
			for (int i = 0; i < value.length(); i++) {
				char charAt = value.charAt(i);
				if (Character.isDigit(charAt))
					hasNumber = true;
				if (Character.isLowerCase(charAt))
					hasLowerCaseLetter = true;
				if (Character.isUpperCase(charAt))
					hasUpperCaseLetter = true;

				int ascii = (int) charAt; // Get ASCII value of the character
				if ((ascii >= 33 && ascii <= 47) || (ascii >= 58 && ascii <= 64) || (ascii >= 91 && ascii <= 96)
						|| (ascii >= 123 && ascii <= 126))
					hasSpecialCharecter = true;
				
				if(hasLowerCaseLetter && hasUpperCaseLetter && hasNumber && hasSpecialCharecter)
				{
					return true;
				}

			}
		}
		return false;
	}

}
