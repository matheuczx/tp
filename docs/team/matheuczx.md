# Matheu Cheo's Project Portfolio Page  
## Overview

TutorSwift is a command-line application for private tutors to manage student records, including lessons, grades, and payments.

It is designed to streamline administrative tasks such as tracking student performance and managing lesson schedules efficiently through a CLI interface.

Given below are my contributions to the project.

---

### Summary of Contributions

---

### Enhancements implemented

**1. List feature (v1.0)**
- Added the ability to list all existing / archived students in the system.
- Justification: This feature improves the product significantly because it provides users with a clear overview of all stored students.

**2. Finding Students (v2.0)**
- Added the ability to find students using flexible search criteria.  
- What it does: allows the user to search for students by name (`n/`), subject (`s/`), and/or academic level (`l/`). The search supports case-insensitive and partial matching, and multiple fields can be combined.  
- Justification: This feature improves the product significantly because users can quickly locate specific students without manually scanning through the entire list, especially as the number of students grows.  
- Highlights: This enhancement required integration with the parser to support prefix-based input and validation to ensure at least one valid field is provided. 
---

#### Code Contributed: [RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=matheuczx&breakdown=true)

---

Project management:
- Contributed to feature development and integration within the team repository

---

Enhancements to existing features:
- Improved parser handling by adding validation for the `find` command (e.g. handling missing or invalid prefixes)
- Added additional test cases to improve reliability of command parsing and execution

---

Documentation:

User Guide:
- Added documentation for `find` and `list` commands (format, examples, expected behaviour, error cases)
- Standardised command documentation format for clarity and consistency

Developer Guide:
- Added implementation details for the `FindCommand` feature
- Added sequence diagrams for:
    - Parser handling of `find` command
    - Execution flow of `FindCommand`

Contributions beyond project team:
- Guided team members on usage of PlantUML for sequence diagrams
---
