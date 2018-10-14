# Jacoco Coverage Badge Maven Plugin

![build](https://travis-ci.org/HandOfGod94/jacoco-cov-badge-maven-plugin.svg?branch=master)
[![Gitter](https://badges.gitter.im/gitterHQ/gitter.svg)](https://gitter.im/jacoco-cov-badge-maven-plugin/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.handofgod94/jacoco-cov-badge-maven-plugin.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.handofgod94%22%20AND%20a:%22jacoco-cov-badge-maven-plugin%22)
[![Coverage Status](https://coveralls.io/repos/github/HandOfGod94/jacoco-cov-badge-maven-plugin/badge.svg?branch=master)](https://coveralls.io/github/HandOfGod94/jacoco-cov-badge-maven-plugin?branch=master)

Checkout Wiki for more info:
[https://github.com/HandOfGod94/jacoco-cov-badge-maven-plugin/wiki](https://github.com/HandOfGod94/jacoco-cov-badge-maven-plugin/wiki)

## Contents

  - [Features](#features)
  - [Introduction](#introduction)
  - [Quickstart](#quickstart)
  - [Build Steps](#build-steps)
  - [Usage](#usage)

## Features

* Automatically reads `jacoco.csv` reports and generates badge from it locally
* Can be integrated as part of the build process
* Auto color badges based on the coverage percentage from jacoco reports

	![veryhigh](./docs/resources/veryhigh.svg) ![high](./docs/resources/high.svg)
	![medium](./docs/resources/medium.svg) ![low](./docs/resources/low.svg)

* Configurable badge keys

	![custom1](./docs/resources/custom1.svg) ![custom2](./docs/resources/custom2.svg)

* Generate badges for various categories from `jacoco` report

  ![instruction](./docs/resources/instruction.svg) ![branch](./docs/resources/branch.svg)
  ![complexity](./docs/resources/complexity.svg)


## Introduction

`jacoco-cov-badge-maven-plugin` is a maven plugin which can generate `svg`,
`png` or `jpg` badges *locally* by calculating code coverage from `jacoco` report.

Jacoco is code coverage utility which generates nice report for
Java Based projects and is generally integrated as maven build plugin.

There are several online utilities like `coveralls`, `shields.io` etc. which
provides badges as a service.

> It is highly inspired from `gh-badges` which `shields.io` also uses for
> generating badges.

## Quickstart

The plugin needs `jacoco.csv` report, for it to function correctly.
So you can bind it to any phase after the `jacoco.csv` reports gets generated.

Although these are configurable, but some assumptions made by the plugin:  
**Default jacoco report location**: `${project.reporting.outputDirectory}/jacoco/jacoco.csv`  
**Default Output location**:`${project.build.directory}/coverage.svg`

```xml
<plugin>
  <groupId>io.github.handofgod94</groupId>
  <artifactId>jacoco-cov-badge-maven-plugin</artifactId>
  <version>1.0.0</version>
  <executions>
    <execution>
      <id>generate-badge</id>
      <!-- Note: Make sure in this phase jacoco csv report available -->
      <phase>post-site</phase>
      <configuration>
        <!-- Optional. Check wiki for more info on config parameters -->
      </configuration>
      <goals>
        <goal>badge</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

## Build Steps
```shell
# To build jar file for plugin
mvn clean package

# To install it in local repo
mvn clean install
```

## Usage
```shell
mvn io.github.handofgod94:jacoco-cov-badge-maven-plugin:1.0.0:badge # if you just want to execute goal
```

See [quickstart](#quickstart) example, if you want to use it inside `pom.xml`

Checkout project wiki for more information.
