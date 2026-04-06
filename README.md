# MultiBank QA Automation Framework

Automated test suite for the [MultiBank Trading Platform](https://trade.mb.io/) built with Selenium WebDriver + Java + TestNG.

---

## Tech Stack
- Java 17
- Selenium WebDriver 4.18
- TestNG 7.9
- ExtentReports 5.1
- WebDriverManager 5.7
- Maven 3.9
- Jackson (JSON test data)

---

## Project Structure
multibank-qa-automation/
├── src/test/java/com/multibank/
│   ├── pages/          # Page Object Model classes
│   ├── tests/          # Test classes
│   └── utils/          # Utilities (Driver, Report, Screenshot)
├── test-data/          # External JSON test data
├── reports/            # Generated HTML reports
├── screenshots/        # Failure screenshots
├── testng.xml          # Test suite config
├── StringCharFrequency.java  # Task 2
└── .github/workflows/  # CI/CD pipeline

---

## Setup Instructions

### Prerequisites
- Java JDK 17
- Maven 3.9+
- Chrome and Firefox browsers

### 1. Clone the repo
```bash
git clone https://github.com/YOUR_USERNAME/multibank-qa-automation.git
cd multibank-qa-automation
```

### 2. Create config file
Create `src/test/resources/config.properties`:
```properties
browser=chrome
base.url=https://trade.mb.io/
login.url=https://trade.mb.io/login
signup.url=https://trade.mb.io/register
implicit.wait=10
explicit.wait=15
screenshot.on.failure=true
```

### 3. Run tests
```bash
mvn test
```

### 4. View report
Open `reports/TestReport.html` in any browser.

---

## Test Coverage

| Test Class | Tests | Description |
|------------|-------|-------------|
| NavigationTest | 3 | Login page load, title, signup link |
| TradingTest | 4 | Platform load, form elements, input validation |
| ContentValidationTest | 4 | Placeholders, links, URL structure, signup page |

**Total: 22 tests across Chrome and Firefox**

---

## Architecture Decisions

- **Page Object Model** — separates page logic from test logic
- **WebDriverManager** — auto manages browser drivers, no manual driver downloads
- **External test data** — all test data in `test-data/testdata.json`, no hardcoded values
- **ThreadLocal WebDriver** — supports parallel execution safely
- **ExtentReports** — rich HTML reports with screenshots on failure

---

## Authentication Note

The MultiBank trading platform requires email OTP verification for every login session. As a professional practice, credentials and session data are excluded from this repository via `.gitignore`. The framework is designed to be immediately runnable once valid credentials are configured in `config.properties`.

---

## Cross-Browser Testing

Tests run on both Chrome and Firefox via `testng.xml` parallel configuration:
```bash
mvn test
```

---

## CI/CD

GitHub Actions pipeline runs on every push to `main`. See `.github/workflows/ci.yml`.

---

## Task 2: String Character Frequency

**File:** `StringCharFrequency.java`

**Run:**
```bash
javac StringCharFrequency.java
java StringCharFrequency
```

**Example:**
- Input: `"hello world"`
- Output: `h:1, e:1, l:3, o:2, :1, w:1, r:1, d:1`

**Assumptions:**
- Case sensitive
- Spaces and special characters are counted
- Order follows first appearance using LinkedHashMap
- Handles null and empty input gracefully