package ui;

import entity.Developer;
import entity.ProjectStatus;
import exception.NotFoundException;
import logger.Logger;
import service.CustomerService;
import service.DeveloperService;
import service.ProjectService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    private final Logger logger;
    private final CustomerService customerService;
    private final DeveloperService developerService;
    private final ProjectService projectService;

    public UI(String path, CustomerService customerService, DeveloperService developerService, ProjectService projectService) {
        this.logger = new Logger(path);
        this.customerService = customerService;
        this.developerService = developerService;
        this.projectService = projectService;
    }

    public int start() throws IOException {
        logger.setWriter();
        logger.log("App started");
        Scanner read = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Choose UI\n1: For Customer \n2: For Manager\n0: Exit\nEnter: ");
                switch (read.nextInt()){
                    case 1 :{
                        uiForCustomers(read);
                        break;
                    }
                    case 2:{
                        break;
                    }
                    case 0:{
                        logger.log("APP STOPPED");
                        System.out.println("EXIT...");
                        return 0;
                    }
                    default:{
                        System.err.println("Wrong input!");
                        break;
                    }
                }

            }
        } catch (InputMismatchException e){
                System.err.println(e.getMessage());
        }
        finally {
            logger.closeWriter();
        }
        return 1;
    }
    //FOR CUSTOMER

    public int uiForCustomers(Scanner read) throws IOException {

        boolean isAuthorize = false;
        long userId = 0;

        try {
            // AUTHORIZE START //
            while(!isAuthorize){
                System.out.print("Hello! Here you can order project by params!\n But first of all you should create you personal" +
                        "account or choose if its exist\n1: Create new\n2: Choose by name\n0: Exit \nEnter: ");
                switch(read.nextInt()){
                    case 1:{
                        System.out.print("Welcome to CREATOR3000\nAll what you need to register its enter your name\nEnter: ");
                        read.nextLine();
                        String name = read.nextLine();
                        if(name.length() > 3){
                            customerService.createCustomer(name);
                            userId = customerService.getCustomerByName(name).getId();
                            System.out.println("Great!");
                            logger.log("Created [CUSTOMER] NAME: " + name + " ID: " + userId);
                            isAuthorize = true;
                        } else{
                            System.err.println("Name length should be biggest than 3");
                            logger.log("ERROR CREATING ACCOUNT WITH BAD NAME " + name);
                        }
                        break;
                    }
                    case 2:{
                        System.out.print("Enter your name to identify your self\nEnter: ");
                        read.nextLine();
                        String name = read.nextLine();
                        userId = customerService.getCustomerByName(name).getId();
                        if(userId != 0){
                            System.out.println("Welcome back " + name + "!");
                            logger.log("AUTHORIZE [CUSTOMER] NAME: " + name + " ID: " + userId);
                            isAuthorize = true;
                        } else{
                            logger.log("BAD AUTHORIZE [CUSTOMER] WITH NAME " + name);
                            System.out.println("User with current name not found...");
                        }
                        break;
                    }
                    case 0:{
                        return 0;
                    }
                    default:{
                        System.err.println("Wrong input!");
                    }
                }
            }
            // AUTHORIZE END //
            while (true){
                System.out.println("Creator2999 to Create new Project request!");
                System.out.println("1: Set name\n2: Add task\n3: Delete task\n4: Save\n 0: Exit");

            }

        } catch (InputMismatchException e){
            System.err.println("Wrong input!");
        } catch (SQLException throwable) {
            System.err.println(throwable.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (NotFoundException e) {
            System.err.println("Customer with current name was NOT FOUND.");
        }
        catch (Exception e){{
            logger.log("DROP APP -> " + e.getMessage());
            logger.closeWriter();
        }
        } finally {
            logger.closeWriter();
            logger.setWriter();
        }


        return 1;
    }
    // DEVELOPERS
}
