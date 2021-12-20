package ui;

import entity.*;
import exception.NotFoundException;
import logger.Logger;
import model.DeveloperModel;
import model.ProjectModel;
import model.RequestModel;
import service.CustomerService;
import service.DeveloperService;
import service.ProjectService;

import java.io.IOException;
import java.io.NotActiveException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                switch (read.nextInt()) {
                    case 1: {
                        uiForCustomers(read);
                        break;
                    }
                    case 2: {
                        uiForManager(read);
                        break;
                    }
                    case 0: {
                        logger.log("APP STOPPED");
                        System.out.println("EXIT...");
                        return 0;
                    }
                    default: {
                        System.err.println("Wrong input!");
                        break;
                    }
                }

            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        } finally {
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
            while (!isAuthorize) {
                System.out.print("Hello! Here you can order project by params!\n But first of all you should create you personal" +
                        "account or choose if its exist\n1: Create new\n2: Choose by name\n0: Exit \nEnter: ");
                switch (read.nextInt()) {
                    case 1: {
                        System.out.print("Welcome to CREATOR3000\nAll what you need to register its enter your name\nEnter: ");
                        read.nextLine();
                        String name = read.nextLine();
                        if (name.length() > 3) {
                            customerService.createCustomer(name);
                            userId = customerService.getCustomerByName(name).getId();
                            System.out.println("Great!");
                            logger.log("Created [CUSTOMER] NAME: " + name + " ID: " + userId);
                            isAuthorize = true;
                        } else {
                            System.err.println("Name length should be biggest than 3");
                            logger.log("ERROR CREATING ACCOUNT WITH BAD NAME " + name);
                        }
                        break;
                    }
                    case 2: {
                        System.out.print("Enter your name to identify your self\nEnter: ");
                        read.nextLine();
                        String name = read.nextLine();
                        userId = customerService.getCustomerByName(name).getId();
                        if (userId != 0) {
                            System.out.println("Welcome back " + name + "!");
                            logger.log("AUTHORIZE [CUSTOMER] NAME: " + name + " ID: " + userId);
                            isAuthorize = true;
                        } else {
                            logger.log("BAD AUTHORIZE [CUSTOMER] WITH NAME " + name);
                            System.out.println("User with current name not found...");
                        }
                        break;
                    }
                    case 0: {
                        return 0;
                    }
                    default: {
                        System.err.println("Wrong input!");
                    }
                }
            }
            // AUTHORIZE END //
            RequestModel request = new RequestModel();
            while (true) {
                System.out.println("Creator2999 to Create new Project request!");
                System.out.print("1: Set name\n2: Add task\n3: Delete task\n4: Check all tasks" +
                        "\n5: Check request\n6: Create Request\n0: Exit(not save changes)\nEnter: ");
                request.setCustomerId(userId);
                switch (read.nextInt()) {
                    case 1: {
                        read.nextLine();
                        System.out.print("Enter name(2 - 40 symbols): ");
                        String name = read.nextLine();
                        if (name.length() > 1 && name.length() < 41)
                            request.setType(name);
                        else System.err.println("Wrong size of name!");

                        break;
                    }
                    case 2: {
                        read.nextLine();
                        System.out.print("Enter Task: ");
                        request.getTasks().add(read.nextLine());
                        break;
                    }
                    case 3: {
                        read.nextLine();
                        System.out.print("Delete Task(number): ");
                        int pos = read.nextInt();
                        if (pos <= request.getTasks().size())
                            request.getTasks().remove(pos - 1);
                        else System.err.println("Task with current ID not found");
                        break;
                    }
                    case 4: {
                        read.nextLine();
                        System.out.println("#TASKS#");
                        for (int i = 0; i < request.getTasks().size(); i++) {
                            System.out.println("#" + (i + 1) + " -> " + request.getTasks().get(i));
                        }
                        break;
                    }
                    case 6: {
                        System.out.println("Create...");
                        projectService.createNewRequest(request);
                        System.out.println("Successfully!");
                        logger.log("CREATE NEW REQUEST [" + request.getType() + "]");
                        return 1;
                    }
                    case 5: {
                        System.out.println(request);
                        break;
                    }
                    case 0: {
                        logger.log(userId + " DROPPED CREATE REQUEST");
                        return 1;
                    }
                }
            }

        } catch (InputMismatchException e) {
            System.err.println("Wrong input!");
            logger.log("ERROR -> " + e.getMessage());
        } catch (SQLException throwable) {
            System.err.println(throwable.getCause() + " " + throwable.getMessage());
            System.err.println(throwable);
            logger.log("ERROR -> " + throwable.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            logger.log("ERROR -> " + e.getMessage());
        } catch (NotFoundException e) {
            System.err.println("Customer with current name was NOT FOUND.");
            logger.log("ERROR -> " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e);
            logger.log("DROP APP -> " + e.getMessage());
        }

        return 1;
    }

    // DEVELOPERS
    public int uiForManager(Scanner read) throws IOException {
        boolean isExit = false;
        while (!isExit) {
            try {
                logger.log("Opened MANAGER UI");
                System.out.print("Hello Manager!\n1: Check all requests\n2: Create project from request(by requestID)" +
                        "\n3: Change project status(by projectId)\n4: Check all projects\n5: Check all Developers with status" +
                        "\n6: Add developer to project\n7: Remove developer from project\n8: Change developer status" +
                        "\n9: Change developer Quality\n10: Decline request(by requestId)\n0: Exit\nEnter: ");

                switch (read.nextInt()) {
                    case 1: {
                        System.out.println(projectService.getAllRequests());
                        break;
                    }
                    case 2: {
                        System.out.print("Create project from request\nEnter request ID: ");
                        Project project = new Project(projectService.getRequestById(read.nextInt()));
                        if (project.getId() == 0)
                            throw new NotFoundException("Request was not found");
                        boolean isExit2 = false;
                        while (!isExit2) {
                            System.out.print("Project Builder\n1: Set Cost\n2: Set DeadLine\n3: Set name\n4: Create\n0:Exit\nEnter:");
                            switch (read.nextInt()) {
                                case 1: {
                                    System.out.println("Set Cost\nEnter: ");
                                    BigDecimal x = read.nextBigDecimal();
                                    if (x.compareTo(BigDecimal.ZERO) == 1)
                                        project.setCost(x);
                                    else System.err.println("Cost should be bigger than 0");
                                    break;
                                }
                                case 2: {
                                    System.out.print("Date format DD.MM.YYYY\nEnter: ");
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                                    read.nextLine();
                                    project.setDeadLine(Date.valueOf(LocalDate.parse(read.nextLine(), formatter)));
                                    break;
                                }
                                case 3: {
                                    System.out.println("Set name\nEnter name: ");
                                    read.nextLine();
                                    project.setType(read.nextLine());
                                    System.out.println("Successfully!");
                                    break;
                                }
                                case 4: {
                                    project.setStatus(ProjectStatus.ACTIVE);
                                    projectService.createNewProject(project.getId(), project);
                                    isExit2 = true;
                                    break;
                                }
                                case 0: {
                                    System.out.println("Exit");
                                    isExit2 = true;
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("Change project status\nEnter project ID: ");
                        long id = read.nextInt();
                        System.out.println("Choose Status\n1: ACTIVE\n2: CLOSED,\n3: EXPIRED\nEnter: ");
                        ProjectStatus projectStatus = ProjectStatus.ACTIVE;
                        switch (read.nextInt()) {
                            case 1: {
                                projectStatus = ProjectStatus.ACTIVE;
                                break;
                            }
                            case 2: {
                                projectStatus = ProjectStatus.CLOSED;
                                break;
                            }
                            case 3: {
                                projectStatus = ProjectStatus.EXPIRED;
                                break;
                            }
                            default: {
                                System.err.println("Wrong input!");
                                break;
                            }
                        }
                        projectService.setProjectStatus(id, projectStatus);
                        System.out.println("Successfully!");
                        break;
                    }

                    case 4: {
                        System.out.println("All projects");
                        for (ProjectModel model : projectService.getAllProjects())
                            System.out.println(model.toString());
                        break;
                    }

                    case 5: {
                        System.out.println("Choose all developers with status: \n1:Active\n2:Dismissed\n0:Exit\nEnter: ");
                        DeveloperStatus developerStatus = null;
                        switch (read.nextInt()) {
                            case 1: {
                                developerStatus = DeveloperStatus.ACTIVE;
                                break;
                            }
                            case 2: {
                                developerStatus = DeveloperStatus.DISMISSED;
                                break;
                            }
                            case 0: {
                                System.out.println("Exit");
                                break;
                            }
                        }
                        if (developerStatus != null)
                            for (Developer developerModel : developerService.getAllDevelopersWithStatus(developerStatus))
                                System.out.println(new DeveloperModel(developerModel));

                        break;
                    }
                    case 6: {
                        System.out.println("Add developer to Project\nEnter project ID:");
                        ProjectModel project = projectService.getProjectById(read.nextInt());
                        if (project == null)
                            throw new NotFoundException("Project with current ID was not found!");
                        Developer developer = developerService.getDeveloperById(read.nextInt());
                        if (developer == null)
                            throw new NotFoundException("Developer with current ID was not found!");

                        developerService.addDeveloperToProject(developer.getId(), project.getId());
                        System.out.println("Developer " + developer.getName() + " successfully added to Project " + project.getType());
                        break;
                    }

                    case 7: {
                        System.out.println("Add developer to Project\nEnter project ID:");
                        ProjectModel project = projectService.getProjectById(read.nextInt());
                        if (project == null)
                            throw new NotFoundException("Project with current ID was not found!");
                        Developer developer = developerService.getDeveloperById(read.nextInt());
                        if (developer == null)
                            throw new NotFoundException("Developer with current ID was not found!");

                        developerService.removeDeveloperFromProject(developer.getId(), project.getId());
                        break;
                    }
                    case 8: {
                        System.out.println("Change developer status\nEnter developer ID:");
                        Developer developer = developerService.getDeveloperById(read.nextInt());
                        if (developer == null)
                            throw new NotFoundException("Developer with current ID was not found!");
                        System.out.println("Choose status\n1: ACTIVE\n2:DISMISSED\n0:Exit\nEnter:");
                        DeveloperStatus developerStatus = null;
                        switch (read.nextInt()) {
                            case 1: {
                                developerStatus = DeveloperStatus.ACTIVE;
                                break;
                            }
                            case 2: {
                                developerStatus = DeveloperStatus.DISMISSED;
                                break;
                            }
                            case 0: {
                                break;
                            }
                        }
                        if (developerStatus != null)
                            developerService.setDeveloperStatus(developer.getId(), developerStatus);
                        break;
                    }
                    case 9: {
                        System.out.println("Change developer Quality\nEnter developer ID:");
                        Developer developer = developerService.getDeveloperById(read.nextInt());
                        if (developer == null)
                            throw new NotFoundException("Developer with current ID was not found!");
                        System.out.println("Choose status\n1: STUDENT\n2:JUINIOR\n3:MIDDLE\n4:SENIOR\n0:Exit\nEnter:");
                        Qualities qualities = null;
                        switch (read.nextInt()) {
                            case 1: {
                                qualities = Qualities.STUDENT;
                                break;
                            }
                            case 2: {
                                qualities = Qualities.JUNIOR;
                                break;
                            }
                            case 3: {
                                qualities = Qualities.MIDDLE;
                                break;
                            }
                            case 4: {
                                qualities = Qualities.SENIOR;
                                break;
                            }
                            case 0: {
                                break;
                            }
                        }
                        if (qualities != null)
                            developerService.setDeveloperQuality(developer.getId(), qualities);
                        break;
                    }
                    case 10: {
                        System.out.println("Decline request\nEnter request ID:");
                        RequestModel project = projectService.getRequestById(read.nextInt());
                        if (project == null)
                            throw new NotFoundException("Request with current id was not found!");
                        projectService.declineRequest(project.getId());
                        break;
                    }
                    case 11: {
                        System.out.print("Create new Developer\nEnter name: ");
                        read.nextLine();
                        String name = read.nextLine();
                        System.out.print("Enter main qualification: ");
                        String mainQuality = read.nextLine();
                        System.out.print("Quality\n1: STUDENT\n2:JUINIOR\n3:MIDDLE\n4:SENIOR\nEnter: ");
                        Qualities qualities = null;
                        switch (read.nextInt()) {
                            case 1: {
                                qualities = Qualities.STUDENT;
                                break;
                            }
                            case 2: {
                                qualities = Qualities.JUNIOR;
                                break;
                            }
                            case 3: {
                                qualities = Qualities.MIDDLE;
                                break;
                            }
                            case 4: {
                                qualities = Qualities.SENIOR;
                                break;
                            }
                        }
                        developerService.newDeveloper(new Developer(mainQuality,name,qualities));
                        break;
                    }
                    case 0: {
                        System.out.println("Exit");
                        isExit = true;
                        break;
                    }
                }

            } catch (InputMismatchException e) {
                System.err.println("Wrong input!");
                logger.log("ERROR -> " + e.getMessage());
            } catch (DateTimeException e) {
                System.err.println(e.getLocalizedMessage());
            } catch (SQLException throwable) {
                System.err.println(throwable.getCause() + " " + throwable.getMessage());
                System.err.println(throwable);
                logger.log("ERROR -> " + throwable.getMessage());
            } catch (IOException e) {
                System.err.println(e.getMessage());
                logger.log("ERROR -> " + e.getMessage());
            } catch (NotFoundException e) {
                System.err.println(e.getMessage());
                logger.log("ERROR -> " + e.getMessage());
            } catch (Exception e) {
                System.err.println(e);
                logger.log("DROP APP -> " + e.getMessage());
            }
        }
        return 1;
    }
}
