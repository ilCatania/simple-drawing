Simple drawing tool
===================

Simple console version of a drawing program, with the following features:
- draw lines and rectangles
- fill them with colours

Assumptions:
- Bucket fill extends to adjacent "pixels" with the same colour horizontally and verticall, but not diagonally
- The "colour" `x` used for lines is treated exactly like all other colours, so a bucket fill with colour `x` followed by another with another colour will clear adjacent lines too

Main commands:
- run the build with `gradlew build`
- generate the binaries with `gradlew assemble`, you will find them in subdirectory `commandline-app/build/distributions`
- run the unit tests with `gradlew test`
- generate the test coverage reports with `gradlew test jacoco`
- for other build commands, check the gradle documentation

Known issues
- the application does not work when running from gradle directly via `gradlew run` because the `Console` object is null. See https://issues.gradle.org/browse/GRADLE-2386
- error handling is very poor, especially on the command line side. Needs refactoring
- unit tests only cover successful scenarios, and do not cover either failing scenarios, bad arguments or tricky scenarios. For a list of useful tests to cover, see the test classes
- javadoc coverage could be improved
- no command line integration tests have been configured
- no logging has been configured (although the required libraries have been included in the build)
- current release version of `cglib` does not support java 8, so the build includes a snapshot version instead
