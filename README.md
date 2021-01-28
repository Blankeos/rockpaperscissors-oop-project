# Rock Paper Scissors Game System
My final project for Object Oriented Programming written in Java. A rock, paper, scissors game system with an account authentication system, profiles, and leaderboards.

## Dependencies
RPSGameSystem.jar can't run without 
- /imgs folder
- accounts.txt
- playerprofiles.txt

> The complete application without the source code is located in out/production/artifacts/RPSGameSystem_jar

## Design Patterns I used
I used MVC for separating the Data, Views, and Functionality. I used Mediator for simplifying communication between scripts by having only Controllers have reference to both Models and Views while other scripts only need to know about the Controllers. I used a mix of Factory and State pattern for Controllers by making them inherit from a base class and instantiating it whenever I need its functionality for a specific state.  I used the Singleton for most of the Manager scripts in this application. They're not the cleanest implementations, however.

**Carlo Antonio Taleon**
BSCS-2A
[GitHub](https://github.com/Blankeos/)