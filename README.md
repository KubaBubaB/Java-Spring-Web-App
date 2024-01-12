
# Java Spring Web app

Project contains stages of devolopment (diffrent branches -  diffrent stages) of a simple Java Spring Web application. Project made for my Internet Services Architecture course of 5th semester of Computure Science studies at GUT. 
## Tech Stack

**Spring Framework**, **Angular**, **Docker**


## Branches Description

| Branch name| Laboratory number|Interaction method|
| :- | :- |:-|
| `main`|  1, 2 | Console commands|
|`lab3`|3|request.http in IDE or http to :8080|
|`lab4`|4|AUIGateway/request.http in IDE or http to :8085|
|`lab5`|5|In web browser|
|`lab6`|6|In web browser|

#### Main
Implementation of first and second laboratory. First laboratory is to be found in AUIlab0, contents of it mainly focus on usage of advanced Java features and have no corelation with next parts of this project. Second laboratory is located in AUIlab1, it contains things such as: JPA, command line runner,  data initializer,  in-memory H2 database, Spring repositoires and Spring Services.
#### Lab3
Basic components of Spring MVC (REST services implementation), Rest Controller, http requests.
#### Lab4
Decomposition of monolithic application into stand-alone modules based on microservices architecture.  Inter-applications event-based communication. Implementation of Spring Cloud Gateway with routing rules.
#### Lab5 
Created Angular Framework based project with
routing module and consuming microservices functionallity.
#### Lab6 
"Dockerized" applications.






## Run Locally

Clone the project

```bash
  git clone https://github.com/KubaBubaB/Sem5
```

#### Main, Lab3 branches:
Hit the green arrow in IDE.

#### Lab4 branch:
Run all of the applications. You have to run the person module before the job module. Send http requests to the gateway.

#### Lab5 branch:
Run all of the Spring applications as in Lab4 branch, also run Angular app in this way (Provided you have NodeJS installed):

Go to the project directory

```bash
  cd Sem5/AUILab1/AUIAngular/src
```

Run Angular with proxy

```bash
  ng serve --proxy-config proxy.conf.json
```
#### Lab6 branch:
In all 4 directories: AUILab4Job, AUILab4Person, AUIGateway, AUIAngular 
#### Run 
```bash
./build.sh
```
Then in the project main directory (Provided you have installed docker)
#### Run
```bash
docker compose up -d
```

