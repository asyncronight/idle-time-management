package me.momarija.bioui.controllers;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandling {

	@ExceptionHandler(value = Exception.class)
	public ModelAndView errorHandler(HttpServletRequest request, Exception ex) throws Exception {
		if(AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null)
			throw ex;
		ModelAndView mav = new ModelAndView();
		if (ex instanceof MultipartException) {
			mav.addObject("exception", new Exception("Image upload échouée, taille de l'image est plus que 1MB."));
		} else {
			mav.addObject("exception", ex);
		}
		mav.addObject("title", ex.getMessage());
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("error/exception");
		return mav;
	}
}
