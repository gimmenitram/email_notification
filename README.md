# Email Notification App
Application that sends random phrases as email notification

# About the App
- Java written application that sends email notifications (the goal was to remind drinking water regularly)
- Notification phrases are fetched from phrases on DynamoDB (random colorful phrases to mimic motivational reminders)
- Phrases saved on DynamoDB are populated using Postman

# Tools Used
- Elastic Beanstalk - service to automatically create a reference environment to mimic and practice on
- EC2 - main service used to serve the function of the app and tinkered configuration on
- Postman - used to communicate and consume endpoints populated in DynamoDB
- DynamoDB - easy and appropriate flat file database for the app
- Linux & IntelliJ - platform chosen to gain more practice using CLI, connecting to cloud tools and services

# Project Walk-through & Conclusion
- The Email Notification App is a project app used to play around and practice more on the cloud infrastructure
- Challenge & Aim:
	- To experience, gain more knowledge, and build on just knowing how to spin-up a single EC2 instance without a proper function
	- Use an automatic service such as Elastic Beantalk to have an environment to mimic, act as a reference, and have more direction in terms of configuration
- Major learnings:
	- Keeping in mind the environment created from Elastic Beanstalk
	- Remembering port configurations that needed to be opened for connection and communication
	- An environment concept where scaling out/auto-scaling group can be appropriately setup for