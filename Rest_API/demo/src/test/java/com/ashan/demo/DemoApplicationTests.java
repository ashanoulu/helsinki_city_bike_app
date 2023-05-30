package com.ashan.demo;

import com.ashan.demo.model.Station;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Test
//	public void createStudentCourse() throws Exception {
//		Station mockStation = new Station();
//		mockStation.setNimi();
//		mockStation.setNamn();
//		mockStation.setName();
//		mockStation.setAddress();
//		mockStation.setAddress();
//
//
//		// studentService.addCourse to respond back with mockCourse
//		Mockito.when(studentService.addCourse(Mockito.anyString(),
//				Mockito.any(Course.class))).thenReturn(mockCourse);
//
//		// Send course as body to /students/Student1/courses
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/students/Student1/courses")
//				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		MockHttpServletResponse response = result.getResponse();
//
//		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//
//		assertEquals("http://localhost/students/Student1/courses/1",
//				response.getHeader(HttpHeaders.LOCATION));
//
//	}

}
