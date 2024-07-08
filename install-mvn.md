# Install Maven
1. Download Maven:
   - Go to the Apache Maven website.
   - Download the latest version of Apache Maven as a ZIP archive. Choose a version with "Binary zip archive" in the download section.

2. Extract Maven Archive:
   - After downloading, extract the contents of the ZIP archive to a location on your computer. For example, you can extract it to C:\Program Files\Apache\.

3. Set Environment Variables:
   - Add Maven's bin directory to the system's PATH environment variable. 
     - Right-click on "This PC" or "Computer" and select "Properties". 
     - Click on "Advanced system settings" on the left side. 
     - Click on the "Environment Variables..." button. 
     - In the "System Variables" section, select the Path variable and click "Edit...". 
     - Add a new entry with the path to Maven's bin directory. For example, C:\Program Files\Apache\apache-maven-3.x.x\bin. 
     - Click "OK" to save the changes.

4. Restart your PC

5. Verify Installation:
   - Open a command prompt.
   - Type mvn -version and press Enter.
   - If Maven is installed correctly, you should see the Maven version and other details printed in the command prompt.
