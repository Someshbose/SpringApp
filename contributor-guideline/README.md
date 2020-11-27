# Contribution - guideline

- Eclipse or IntelliJ IDE is advised for development. Make sure you have jdk 11 installed and use the same in IDE. 

- Install lombok plugin otherwise you will get error. Follow  [this url](https://projectlombok.org/setup/eclipse) for installation.

- Import this project as Maven project.

- here master is the deployment branch and development is for development activity. So create/cut new branch from development.

- here we are using Builder Pattern for creating object for entities and DTOs.

- please follow below branch name strategy. 

```sh
For features -> ft-<branch-name>
For bug -> bg-<branch-name>
For documentation -> doc-<branch-name>  
```
- Raise all MR against development branch. Per MR please keep atmost 15 files changes.

- Variable & function name should be camle-case and meaningful.

- Class name should start with Capital letter.

- Please try to add Junits (here we use JUnit5) for all the changes you have done.
 
> N.B- Before raising an MR please run local build first.
