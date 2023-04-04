# WDIO + Axe sample
The project demonstrates a WDIO + Axe with Perfecto

</br>

# Prerequisites

Make sure you have installed:

Install npm

Install Node 19.x+

## Getting started
- Navigate to wdio folder in terminal/ command prompt. 
- Run the below command to install node dependencies with this command:

      npm install

</br>

## Configuring Perfecto details

1. Open wdio.conf.js within web folder.
   
2. Replace <\<cloud name>> with your perfecto cloud name (e.g. demo is the cloudName of demo.perfectomobile.com).

3. Replace <\<security token>> with your perfecto security token.

4. Set web capabilities.

</br>

## Running the test

Navigate to wdio folder in terminal/ command prompt. 

Run the below command to execute the web:

    npm run web

</br>

## CI Integration

Run the following commands to integrate with Smart Reporting CI Dashboard:

    jobName=${JOB_NAME} jobNumber=${BUILD_NUMBER} npm run web

where \${JOB_NAME} corresponds to job name and \${BUILD_NUMBER} corresponds to job number.

## Help

Please reach out to [Perfecto support](https://www.perforce.com/support/request-support) in case of any support.