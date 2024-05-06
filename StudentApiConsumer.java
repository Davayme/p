/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


public class StudentApiConsumer implements CrudRepository<Student>{

    private final String URL = "http://localhost/Quinto/apip/Controllers/apiEstudiantes.php";
    
    @Override
    public List<Student> getAll() {
        RestTemplate restemplate = new RestTemplate();
        Student[] students  = restemplate.getForObject(URL, Student[].class);
        return students != null ? Arrays.asList(students): Collections.emptyList();  
    }

    @Override
    public boolean create(Student student) {
       RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Student> requestEntity = new HttpEntity<>(student);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(this.URL, HttpMethod.POST, requestEntity, Void.class);
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }

    @Override
    public boolean update(Student student) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.URL)
                .queryParam("cedula", student.getID())
                .queryParam("nombre", student.getFirstname())
                .queryParam("apellido", student.getLastname())
                .queryParam("direccion", student.getAddress())
                .queryParam("telefono", student.getPhone());
        
        String updateUrl = builder.toUriString();
        ResponseEntity<Void> responseEntity = restTemplate.exchange(updateUrl, HttpMethod.PUT, null, Void.class);
        return responseEntity.getStatusCode() == HttpStatus.OK;
     
    }


    @Override
    public boolean delete(String ID) {
       RestTemplate restTemplate = new RestTemplate();
       UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.URL)
               .queryParam("cedula", ID);
       String deleteURL = builder.toUriString();
       ResponseEntity<Void> responseEntity = restTemplate.exchange(deleteURL, HttpMethod.DELETE, null, Void.class);
       return responseEntity.getStatusCode() == HttpStatus.OK;
             
    }


    
}
/*
         <dependencies>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.17.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>6.1.6</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>6.1.6</version>
        </dependency>
        
        <dependency>
            <groupId>com.formdev</groupId>
            <artifactId>flatlaf</artifactId>
            <version>3.4.1</version>
            <type>jar</type>
        </dependency>
        
    </dependencies>
 */