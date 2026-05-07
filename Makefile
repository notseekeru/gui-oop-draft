# Shortcut for a shortcut
# Usage:
#   make run
#   make verify

# prerequisite: download make for Windows from https://gnuwin32.sourceforge.net/packages/make.htm and add it to your PATH or install by chocolatey or winget

SHELL := powershell.exe
.SHELLFLAGS := -NoProfile -Command

RUNTIME_IMAGE := $(subst \\,/,$(patsubst %\\,%,$(JAVA_HOME)))
APP_NAME ?= GUI-OOP-DRAFT-APP
ARTIFACT_ID ?= gui-oop-draft
MAIN_CLASS ?= Launcher
APP_VERSION ?= 1.0.0
APP_JAR ?= $(ARTIFACT_ID)-$(APP_VERSION).jar
DIST_DIR ?= target/dist

reset:
	@if (Test-Path -Path target) { Remove-Item -Recurse -Force target }
	@if (Test-Path -Path shapora.db) { Remove-Item -Recurse -Force shapora.db }
	@if (Test-Path -Path dependency-reduced-pom.xml) { Remove-Item -Recurse -Force dependency-reduced-pom.xml }
	

run:
ifeq ($(OS),Windows_NT)
	mvnw.cmd javafx:run
else
	./mvnw javafx:run
endif


crun:
ifeq ($(OS),Windows_NT)
	mvnw.cmd clean javafx:run
else
	./mvnw clean javafx:run
endif

spota:
ifeq ($(OS),Windows_NT)
	mvnw.cmd spotless:apply
else
	./mvnw clean javafx:run
endif

verify:
	@if (Test-Path -Path target/dist) { Remove-Item -Recurse -Force target/dist }
	mvnw.cmd verify

cverify:
	@if (Test-Path -Path target/dist) { Remove-Item -Recurse -Force target/dist }
	mvnw.cmd clean verify

depa:
	mvnw.cmd clean dependency:analyze

dept:
	mvnw.cmd clean dependency:tree

make sure:
	@echo $(JAVA_HOME)

# Package the application using jpackage and copy the resulting JAR to the target/dependency directory, ensure you have Liberica Full JDK installed and set as JAVA_HOME
.PHONY: package
package:

	@if (Test-Path -Path $(DIST_DIR)) { Remove-Item -Recurse -Force $(DIST_DIR) }

	mvnw.cmd clean package

	jpackage --type app-image --name "$(APP_NAME)" --input target --main-jar "$(APP_JAR)" --main-class "$(MAIN_CLASS)" --runtime-image "$$($$env:JAVA_HOME -replace '\\$$', '')" --dest "$(DIST_DIR)"