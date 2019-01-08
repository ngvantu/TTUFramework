package com.ttu.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttu.client.model.School;
import com.ttu.client.model.Student;

import ttuframework.common.Converter;
import ttuframework.connection.TTUConnection;
import ttuframework.mapper.SQLMapper;

@Controller
public class CommonController {
	
	@Autowired
	TTUConnection cnn;

	@GetMapping(value = {"/student", ""})
	public ModelAndView loadIndexPage(){
		ModelAndView mav = new ModelAndView("index.html");
		return mav;
	}
	
	@GetMapping("/school")
	public ModelAndView loadAllSchool(){
		ModelAndView mav = new ModelAndView("school.html");
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/student/all", method = RequestMethod.POST)
	public JSONObject getAllStudent(){
		JSONObject response = new JSONObject();
		List<Student> listStudent = new ArrayList<>();
		List<String> listFields = new ArrayList<>();
		listFields = new SQLMapper(Student.class).getColumnNames();
		List<Object> list = cnn.select(Student.class).allRows().run();
		
		for(Object o : list){
			try {
				listStudent.add(Converter.ObjectToClass(o, Student.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.put("listFields", listFields);
		response.put("listStudent", listStudent);
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/school/all", method = RequestMethod.POST)
	public JSONObject getAllSchool(){
		JSONObject response = new JSONObject();
		List<School> listSchool = new ArrayList<>();
		List<String> listFields = new ArrayList<>();
		listFields = new SQLMapper(School.class).getColumnNames();
		List<Object> list = cnn.select(School.class).allRows().run();
		
		for(Object o : list){
			try {
				listSchool.add(Converter.ObjectToClass(o, School.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.put("listFields", listFields);
		response.put("listSchool", listSchool);
		return response;
	}
	
	@GetMapping("/student/{id}/delete")
	public ModelAndView deleteStudentById(@PathVariable("id") String id){
		List<Object> student = cnn.select(Student.class).addWhere("idStudent='" +id +"'").run();
		cnn.delete(student.get(0));
		return new ModelAndView("redirect:/student");
	}
	
	@GetMapping("/school/{id}/delete")
	public ModelAndView deleteSchoolById(@PathVariable("id") String id){
		List<Object> school = cnn.select(School.class).addWhere("idSchool='" +id +"'").run();
		cnn.delete(school.get(0));
		return new ModelAndView("redirect:/school");
	}
	
	@GetMapping("/student/{id}/edit")
	public ModelAndView editStudentById(@PathVariable("id") String id){
		ModelAndView mav = new ModelAndView("updateStudent.html");
		return mav;
	}
	
	@GetMapping("/student/insert")
	public ModelAndView insertStudent(){
		ModelAndView mav = new ModelAndView("insertStudent.html");
		return mav;
	}
	
	@GetMapping("/school/{id}/edit")
	public ModelAndView editSchoolById(@PathVariable("id") String id){
		ModelAndView mav = new ModelAndView("updateSchool.html");
		return mav;
	}
	
	@GetMapping("/school/insert")
	public ModelAndView insertSchool(){
		ModelAndView mav = new ModelAndView("insertSchool.html");
		return mav;
	}
	
	@PostMapping("/student/update")
	public ModelAndView updateStudent(@Valid Student student, 
			BindingResult bindingResult, HttpServletRequest request) {
		List<Object> school = cnn.select(School.class).addWhere("idSchool='" +request.getParameter("idSchool") +"'").run();
		student.setIdSchool((School) school.get(0));
		cnn.update(student);
		ModelAndView mav = new ModelAndView("redirect:/student");
		return mav;
	}
	
	@PostMapping("/school/update")
	public ModelAndView updateSchool(@Valid School school, 
			BindingResult bindingResult, HttpServletRequest request) {
		cnn.update(school);
		ModelAndView mav = new ModelAndView("redirect:/school");
		return mav;
	}
	
	@PostMapping("/student/insert")
	public ModelAndView insertStudent(@Valid Student student, 
			BindingResult bindingResult, HttpServletRequest request) {
		List<Object> school = cnn.select(School.class).addWhere("idSchool='" +request.getParameter("idSchool") +"'").run();
		student.setIdSchool((School) school.get(0));
		cnn.insert(student);
		ModelAndView mav = new ModelAndView("redirect:/student");
		return mav;
	}
	
	@PostMapping("/school/insert")
	public ModelAndView insertSchool(@Valid School school, 
			BindingResult bindingResult, HttpServletRequest request) {
		cnn.insert(school);
		ModelAndView mav = new ModelAndView("redirect:/school");
		return mav;
	}
}
