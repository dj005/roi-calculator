# ROI Calculator

This project contains three AEM components which are as follows
## Frontend coding challenge - ROI Calculator
This AEM component calculates ROI based on user inputs using JS.
## Backend coding challenge - ROI Calculator & history.
This AEM component calculates and save ROI based on user input under `/var/demosite/roi/{userId}`. Historical data is fetched from node storage and displayed using Sling Servlet.

## Pre-requisite

* AEM running on local.
* Npm/Java 11/maven installed.

## Steps to run

* Download project from below GitHub location.

> https://github.com/dj005/roi-calculator

* Compile and upload in AEM using below command.

> mvn clean install -PautoInstallPackage

* Go to below links to view ROI calculator implementation:
	* Backend project (ROI Calculator and ROI history):
`http://localhost:4502/content/demosite/us/en/roicalcv2.html`
`http://localhost:4502/content/demosite/us/en/roihistory.html`
	* Frontend project (ROI Calculator):
`http://localhost:4502/content/demosite/us/en/roicalcv1.html`

## Screenshots

* ROI-Calculator-1-Form
![ROI-Calculator-1-Form](https://github.com/dj005/screenshots/blob/main/ROI-Calculator-1-Form.png "ROI-Calculator-1-Form")

* ROI-Calculator-1-Results
![ROI-Calculator-1-Results](https://github.com/dj005/screenshots/blob/main/ROI-Calculator-1-Results.png "ROI-Calculator-1-Results")

* ROI-Calculator-2-Form
![ROI-Calculator-2-Form](https://github.com/dj005/screenshots/blob/main/ROI-Calculator-2-Form.png "ROI-Calculator-2-Form")

* ROI-History-1-Form
![ROI-History-1-Form](https://github.com/dj005/screenshots/blob/main/ROI-History-1-Form.png "ROI-History-1-Form")

* ROI-History-1-Results
![ROI-History-1-Results](https://github.com/dj005/screenshots/blob/main/ROI-History-1-Results.png "ROI-History-1-Results")

* CRX-roi-data
![CRX-roi-data](https://github.com/dj005/screenshots/blob/main/CRX-roi-data.png "CRX-roi-data")