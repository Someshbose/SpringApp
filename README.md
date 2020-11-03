# SpringApp -FileUploader
[![Build Status](https://travis-ci.org/Someshbose/FileUploader.svg?branch=development)](https://travis-ci.org/Someshbose/FileUploader)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A Microservice to upload file.

To build the project in local
---
```sh
mvn clean package
```
To create a docker image of the app
---
```sh
mvn package dockerfile:build
```
To run the app in local 
---
- Run kafka in local(optional)
localhost:9092 - default kafka 
- start the application
* through dockerImage itself.
* importing the project and running through ide.
- You can see welcome message on following url.
```sh
http://localhost:8080/app/service/hello
```
- For more information related to application.
```sh
http://localhost:8080/app/service/actuator
```
- For Publishing something on kafka. You can see the output on console itself.
```sh
http://localhost:8080/app/service/mesaage/<msg payload>
```

- h2 db embedded console can be found on below url.
```sh
http://localhost:8080/app/service/h2-console
```

- File Uploader post upload url.
```sh
http://localhost:8080/app/service/filestore

body:
{
    "charSet":"UTF-8",
    "correlationId":"123",
    "fileContent":"odia",
    "fileName":"somesh.txt",
    "fileTypeCode":"ABC",
    "submitterEmail":"somesh.bose@gmail.com",
    "fieldSeparator":","
}
```

- File Uploader get url.
```sh
localhost:8080/app/service/findbyId/<ref-id>
```

- Contributions guideline-
Please read [Contribution.md](https://github.com/Someshbose/SpringApp/tree/development/contributor-guideline) 
