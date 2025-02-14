# Selenium TestNG Framework

This project is a Selenium-based test automation framework using TestNG and Java 11.  It provides a foundation for creating robust and maintainable UI tests.

## Technologies Used

* **Selenium:** For browser automation.
* **TestNG:** For test orchestration and reporting.
* **Java 11:** The programming language used.
* **Apache POI:** For working with Excel files (e.g., test data).
* **Extent Reports:** For generating detailed test reports.
* **Log4j:** For logging.

## Getting Started

1. **Prerequisites:**

    * Java 11 JDK installed and configured (JAVA_HOME and PATH environment variables set).
    * Maven installed.

2. **Clone the repository:**

   ```bash
   git clone git@github.com:Aadil4u/douglas-assignment.git

3. **Navigate to the project directory:**

    ```bash
   cd douglas-assignment
   
4. **Run the tests:**
   * To run the tests defined in Filters.xml, use the following Maven command:
    ```bash
   mvn test -Dmaven.test.suite.file=Filters.xml -Dheadless=false
5. **View the reports**
   * Extent Reports are generated in the reports directory. Check the HTML reports there.