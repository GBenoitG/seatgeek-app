# Project SeatGeek app

This project uses SeatGeek's API.

## Git flow

The project has a git flow to create new features and releases.

`dev` is the main branch until the first release.

All features are named like `feature/your-feature-name` and based on `dev`, then create a PR with reviewers to merge the feature on `dev`.

## Project architecture

This project is organized in accordance with the clean architecture standards with the following modules:

- *App*: contains all of Android dependent code. That will include the functioning of the app as well as the presentation layer.
- *Data*: contains the abstract definition of the different data sources, like repositories
- *Domain*: contains all business objects and rules, a purely Kotlin module.
- *Framework*: contains all code that would require an interaction with a framework as well as the implementation of the data module. It will be split into storage and network packages.

### Dependencies
All dependencies are declared in one file: `dependencies.gradle` placed in the root folder.

This file is divided into two main parts: an object `versions` to manage versions of any libraries, and the rest of the file for dependencies of libraries.

> ⚠ Some versions are factorized and stored in the file: `gradle.properties`, the reason behind this, is to use these versions in the root `build.gradle`.

This file is implemented into each `build.gradle` which needs dependencies.

### Environment

This template allows to use multiple environment thanks too flavors. All flavors are declared in the `flavors.gradle` file, locate in the root project.
