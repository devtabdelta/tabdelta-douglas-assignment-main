# Selenium TestNG Framework

A robust test automation framework built with Selenium WebDriver and TestNG, featuring Page Object Model design pattern, data-driven capabilities, and extensive reporting.

## 🚀 Features

- Page Object Model (POM) design pattern
- Data-driven testing using Excel
- Extent Reports integration
- Parallel test execution
- Retry mechanism for failed tests
- Cross-browser testing support
- GitHub Actions CI/CD integration
- Log4j2 logging
- Configuration management

## 📁 Project Structure

```
Selenium_TestNG_Framework/
├── .github/workflows/         # GitHub Actions workflow
├── configuration/             # Framework configuration files
├── reports/                   # Test execution reports
├── src/
│   ├── main/java/
│   │   ├── base/             # Test base setup
│   │   ├── browserFactory/    # Browser management
│   │   ├── dataProvider/     # Data providers
│   │   ├── enums/            # Framework enums
│   │   ├── helper/           # Utility classes
│   │   ├── listeners/        # TestNG listeners
│   │   ├── pages/            # Page Object classes
│   │   └── resources/        # Framework resources
│   └── test/
│       └── java/testcases/   # Test classes
├── test-suite/               # TestNG XML suites
└── testdata/                 # Test data files
```

## 🛠️ Prerequisites

- Java JDK 11 or higher
- Maven 3.8.x or higher
- Chrome/Firefox/Edge browser
- IDE (IntelliJ IDEA recommended)

## ⚙️ Setup & Configuration

1. Clone the repository:

```bash
git clone git@github.com:Aadil4u/douglas-assignment.git
```

2. Install dependencies:

```bash
mvn clean install
```

3. Configure `configuration/config.properties`:

```properties
url=https://www.douglas.de/de
```

## 🏃‍♂️ Running Tests

### Via Maven

Run all tests:

```bash
    mvn test "-DsuiteFile=Filters.xml" -Dheadless=false -Dbrowser=chrome -DrunOnRemote=false # Run test in headed mode locally

    mvn test "-DsuiteFile=Filters.xml" -Dheadless=true -Dbrowser=chrome -DrunOnRemote=false # Run test in headless mode locally

    mvn test "-DsuiteFile=Filters.xml" -Dheadless=false -Dbrowser=firefox -DrunOnRemote=false # Run test in firefox

    mvn test "-DsuiteFile=Filters.xml" -Dheadless=false -Dbrowser=chrome -DrunOnRemote=true # Run test on saucelabs in chrome
```

### Via TestNG XML

Run the TestNG suite directly from your IDE by right-clicking on the XML file in `test-suite/` directory.

## 📊 Reporting

- Extent Reports are generated in `reports/` directory
- Screenshots for failed tests are captured automatically

## 🔄 CI/CD Integration

The framework includes GitHub Actions workflow configuration in `.github/workflows/Run_Tests.yaml` for automated test execution.

## 📦 Key Components

### Base Package

- `BaseClass.java`: Handles driver initialization and common test setup/teardown

### Browser Factory

- `BrowserFactory.java`: Manages WebDriver instance creation for different browsers

### Data Providers

- `CustomDataProvider.java`: TestNG data providers
- `ExcelReader.java`: Excel data reading utility

### Listeners

- `ExtentManager.java`: Manages Extent Reports instance
- `ExtentTestNGITestListener.java`: TestNG listener for reporting
- `RetryListener.java`: Handles test retry mechanism

### Page Objects

- `HomePage.java`: Home page interactions
- `ProductsPage.java`: Products page interactions

## Project Dependencies

- Selenium WebDriver
- TestNG
- Extent Reports
- Apache POI
- Log4j2

## Environment Variables

To run the test on saucelabs, you will need to add the following environment variables in your system

- `SAUCE_USERNAME`
- `SAUCE_ACCESS_KEY`
- `SAUCE_PLATFORM`
- `SAUCE_BROWSER_VERSION`
- `SAUCE_BUILD`
