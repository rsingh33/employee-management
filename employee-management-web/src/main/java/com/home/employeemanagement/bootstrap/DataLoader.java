package com.home.employeemanagement.bootstrap;

import com.home.employeemanagement.model.Department;
import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.model.Project;
import com.home.employeemanagement.model.Stage;
import com.home.employeemanagement.repositories.DepartmentRepository;
import com.home.employeemanagement.repositories.EmployeeRepository;
import com.home.employeemanagement.repositories.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;

    public DataLoader(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }


    public void loadData() {
        log.debug("Loading Employees!!!!!");

        Employee rahul = new Employee();
        rahul.setEmail("rsingh33@buffalo.edu");
        rahul.setFirstName("Rahul");
        rahul.setLastName("Singh");

        Employee gaurav = new Employee();
        gaurav.setEmail("gsingh33@buffalo.edu");
        gaurav.setFirstName("Gaurav");
        gaurav.setLastName("Singh");

        Employee pria = new Employee();
        pria.setEmail("pria.pundir@buffalo.edu");
        pria.setFirstName("Pria");
        pria.setLastName("Pundir");

        Employee savedRahul = employeeRepository.save(rahul);
        Employee savedPria = employeeRepository.save(pria);
        Employee savedGaurav = employeeRepository.save(gaurav);

        log.debug("Loading Department!!!!!");

        Department humanResources = new Department();
        humanResources.setName("HR");

        Department technology = new Department();
        technology.setName("IT");


        humanResources.addEmployee(savedGaurav);
        humanResources.addEmployee(savedRahul);

        technology.addEmployee(savedPria);


        log.debug("Setting Up  Department!!!!!");

        departmentRepository.save(humanResources);
        departmentRepository.save(technology);

        savedRahul.setDepartment(humanResources);
        employeeRepository.save(savedRahul);

        savedPria.setDepartment(humanResources);
        employeeRepository.save(savedPria);

        savedGaurav.setDepartment(technology);
        employeeRepository.save(savedGaurav);

        log.debug("Setting Up Projects");

        Project omc = new Project();
        omc.setDescription("Client Onboarding Portal");
        omc.setName("OMC");
        omc.setStage(Stage.IN_PROGRESS);

        Project kyc = new Project();
        kyc.setDescription("Know Your Customer");
        kyc.setName("KYC");
        kyc.setStage(Stage.ASSIGNED);

        savedRahul.addProject(omc);
        employeeRepository.save(savedRahul);

        savedPria.addProject(projectRepository.findById(1L).get());
        employeeRepository.save(savedPria);

        savedGaurav.addProject(kyc);
        employeeRepository.save(savedGaurav);

//        saved.addProject(projectRepository.findById(2L).get());
//        employeeRepository.save(saved);


/////////////////////////////////////////////////////////////////////////////////////
 /*       Employee john = new Employee();
        john.setEmail("john@buffalo.edu");
        john.setFirstName("John");
        john.setLastName("Singh");

        Employee jack = new Employee();
        jack.setEmail("gsingh33@buffalo.edu");
        jack.setFirstName("jack");
        jack.setLastName("Singh");

        Project space = new Project();
        space.setDescription("SPACE PROJECT");
        space.setName("SPACE");
        space.setStage(Stage.NOT_STARTED);

        Project mars = new Project();
        mars.setDescription("PROJECT MARS");
        mars.setName("SPACE");
        mars.setStage(Stage.ASSIGNED);

        space.addEmployee(jack);
        space.addEmployee(john);

        mars.addEmployee(jack);
        mars.addEmployee(john);

        jack.setProjects(Arrays.asList(mars, space));
        john.setProjects(Arrays.asList(mars, space));

        employeeRepository.save(john);
        employeeRepository.save(jack);

        projectRepository.save(space);
        projectRepository.save(mars);*/

    }
}
