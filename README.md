# Boulder Bucket List

## Index
[Brief](#brief)
 * [Solution](#solution)
 
[Architecture](#architecture)
 * [Entity Relationship Diagram](#erd)
 * [Architecture Diagram](#arch)

[Testing](#testing)
 * [Report](#report)
 
[Deployment](#deployment)
 * [Technologies](#tech)

[Front end](#frontEnd)

[Future Development](#future)

[Author](#author)

<a name="brief"></a>
## Project Brief
 
To create an OOP-based application with utilisation of supporting tools, methodologies and technologies that encapsulate all core modules covered during training.
 
<a name="solution"></a>
## My Solution
 
I chose to create an application for climbers to create a bucket list of boulders they wish to climb, and to track their progress on different boulders over time.

<a name="architecture"></a>
## Architecture

<a name="erd"></a>
### Entity Relationship Diagram
![ERD](/Documentation/erd.png)

This is a diagram of the entities in my final application and the relationship between them.

![ERD Future](/Documentation/erdFuture.png)

This is my idea for the ERD if I implement the notes functionality as discussed [here](#future).

<a name="arch"></a>
![Architecture Diagram](/Documentation/architecture.png)

This is a high level diagram of the system architecture of my application, excluding the classes found in each package.

<a name="testing"></a>
## Testing

I used JUnit, Mockito and Selenium for unit, integration, and frontend testing respectively, and SonarQube for static reporting.

<a name="report"></a>
### Report


<a name="deployment"></a>
## Deployment
![CI/CD Pipeline](/Documentation/CICDPipeline.png)

The Boulder Bucket List is deployable both locally and through a virtual machine, using Jenkins to automate the process of building, testing and deploying. This process is triggered by updates to the GitHub repository.

<a name="tech"></a>
### Technologies

The application was created using the following technologies:
 * H2 - Database
 * Java - Backend Logic
 * JavaScript, CSS, HTML - Frontend
 * [GitHub](https://github.com/j97b/boulder-bucket-list.git) - Version Control
 * Jenkins - CI Server
 * Maven - Build Tool
 * AWS - Deployment Environment
 * Jacoco, Surefire, SonarQube - Test Reporting
 * [Trello](https://trello.com/b/smf3HVHB/bouldering-bucket-list) - Project Management/Tracking

<a name="frontEnd"></a>
## Initial Front End Design
![Bucket List Wireframe](/Documentation/bucketListPage.png)

This is the wireframe for my initial design of the bucket list page.

![Add Climb Wireframe](/Documentation/addClimb.png)


![Login Page Wireframe](/Documentation/loginPage.png)

A full set of wireframes with attached user stories can be found on my trello board [here](https://trello.com/b/smf3HVHB/bouldering-bucket-list).

<a name="future"></a>
## Future Development

The next step for me is to refine the login system, giving users a password and adding user verification to view their bucket list. I would also like to change the login process, as at the moment it is not very secure notwithstanding the lack of a password, because anyone can easily see all the users who have a bucket list.

I would also like to add a note system, where each boulder can have a number of notes attached to them, giving users the possibilty to create a diary of each climb, or to note down specific directions for a hard to find boulder. Another idea would be to improve on the location functionality, so each boulder could be assigned coordinates, and the user can see a map with the boulder location shown on the map. This could allow users to find boulders more easily.

<a name="auth"></a>
## Author

James Barron
