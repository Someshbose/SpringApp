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

For additional kafka command details click [here](https://github.com/Someshbose/SpringApp/tree/development/kafka-commands)

- start the application
* through dockerImage itself.
* importing the project and running through ide.
* For Maven profile(pom.xml) change the DEVELOPMENT as active instead of TEST profile.
```sh
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
```

- For more information related to application.
```sh
http://localhost:8080/app/service/actuator
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

- After file successfully Uploaded we will see a Message on kafka topic fileUploaded-notify

- File Status Consumer Message template on topic filestatus 

```sh
{
   "eventName":null,
   "eventDate":"2020-11-13T07:32:46.869779Z",
   "fileLocation":"88e437117577455a9d90049cab1d69a7",
   "fileName":"somesh.txt",
   "fileTypeCode":"ResultType",
   "status":"PROCESSED",
   "serviceName":"FileUploaderConsumer"
}

```

- Contributions guideline-
Please read [Contribution.md](https://github.com/Someshbose/SpringApp/tree/development/contributor-guideline) 