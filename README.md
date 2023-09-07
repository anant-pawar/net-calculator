![Build Deploy Workflow](https://github.com/anant-pawar/net-calculator/actions/workflows/build.yaml/badge.svg)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=net-calculator&metric=coverage)](https://sonarcloud.io/summary/new_code?id=net-calculator)

# Net Calculator

Simple library to calculate the net price for provided gross price and country code.

# Implementation Details

* Developed using Java 17 & Maven
* Module details :
    * net-calculator-library : provides implementation for calculating the net price.
    * net-calculator-example : uses net-calculator-library to calculate net price for provided gross price and country
      code, example follows
  ```
      final TaxRateProvider provider = new FileBasedTaxRateProvider();
      final NetCalculator calculator = new DefaultNetCalculator(provider);

      final Double netPrice = calculator.calculateNetPrice(100.0, "DE");

      System.out.println("Net price for gross 100.0 and country Germany is : " + netPrice);
  ```
* Build and Install :  `./mvnw clean install`
* Run :  `./mvnw exec:java -pl net-calculator-example -D exec.mainClass=org.gsg.Main`

# GitHub Actions

* Integrated with GitHub Actions to build and deploy service
    * [Build and Deployment Workflow](https://github.com/anant-pawar/net-calculator/actions)

# Quality and Coverage

* Integrated with SonarCloud using GitHub Actions to generate quality and coverage
  report : [Quality and Coverage Report](https://sonarcloud.io/summary/overall?id=net-calculator) 