package com.spring.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.webflux.bean.Employee;
import com.spring.webflux.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void create(Employee e) {
		employeeRepository.save(e).subscribe();
	}
	
	public Mono<Employee> findById(Integer id) {
		return employeeRepository.findById(id);
	}
	
	public Flux<Employee> findByName(String name) {
		return employeeRepository.findByName(name);
	}
	
	public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }
	
	public Mono<Employee> update(Employee e) {
        return employeeRepository.save(e);
    }
 
    public Mono<Void> delete(Integer id) {
        return employeeRepository.deleteById(id);
    }
}
