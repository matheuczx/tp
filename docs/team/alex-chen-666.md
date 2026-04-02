# Chen Zhendong's Project Portfolio Page

## Project: TutorSwift
## Overview

TutorSwift is a command-line application for private tutors to manage student records, including lessons, grades, and payments.

It is designed to streamline administrative tasks such as tracking student performance and managing lesson schedules efficiently through a CLI interface.

Given below are my contributions to the project.

## Summary of Contributions

### 1. Code Contributed
[Link to my code on the tP Code Dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=alex-chen-666&breakdown=true)

### 2. Enhancements Implemented

#### Major Enhancement: Core Student Management System (Add Command)
*   **What it is**: Developed the primary data entry mechanism that allows tutors to register new student profiles into the system with structured attributes.
*   **Features**: Implemented the `add` command, which supports mandatory prefixes for student names (`n/`), academic levels (`l/`), and subjects (`sub/`).
*   **Justification**: This is the foundational feature of TutorSwift. Every other functionality—such as grade tracking, lesson scheduling, and financial management—depends on the accurate and structured entry of student data.
*   **Technical Depth**:
    *   **Robust Input Parsing**: Integrated the command into a centralized `Parser` using custom prefix-extraction logic, allowing users to input parameters in any order, which enhances CLI flexibility.
    *   **Data Integrity Validation**: Implemented strict defensive checks to prevent the addition of duplicate student names, ensuring that the database remains a reliable "single source of truth."
    *   **Defensive Design**: Employed a combination of `TutorSwiftException` for handling user input errors and `assert` statements to verify internal state invariants during the student instantiation process.

#### Major Enhancement: Student Archiving System
*   **What it is**: Introduced a dual-list architecture to manage "Active" vs. "Archived" students.
*   **Features**: Implemented `archive`, `unarchive`, `list-archive`, and `delete-archive` commands.
*   **Justification**: Tutors frequently deal with students who stop tuition or graduate. This system allows tutors to keep their primary workspace clean while preserving historical records (grades, remarks, etc.) for future reference.
*   **Technical Depth**:
    *   Refactored the core `StudentList` to handle two separate internal `ArrayList` objects.
    *   Ensured data integrity by validating indices across different lists.
    *   Upgraded the `find` command to search across both active and archived repositories simultaneously.

#### Major Enhancement: Persistent Local Storage
*   **What it is**: Developed the `Storage` component to automatically save and load student data to a local text file (`./data/tutorswift.txt`).
*   **Justification**: Manual saving is prone to human error. This feature ensures no data is lost even if the application is closed unexpectedly.
*   **Technical Depth**:
    *   Designed a custom pipe-separated encoding format: `Name | Level | Subject | isArchived | Grades | Remark | FeeRecord`.
    *   **Robustness**: Implemented a "skip-and-log" mechanism for corrupted lines in the storage file, preventing a single malformed entry from crashing the entire database.
    *   **Infrastructure**: Added automatic directory and file creation logic on startup.

#### Minor Enhancement: System Logging and Console Clean-up
*   Integrated `java.util.logging` to track key events.
*   Configured the root logger to suppress system-level `INFO` red-text from the console, providing a cleaner, more professional CLI experience for the user while maintaining diagnostic capabilities in the background.

### 3. Contributions to the User Guide (UG)
*   Documented the **Adding a Student** feature, emphasizing unique name constraints.
*   Wrote the detailed section for the **Archiving System**, explaining the workflow between active and archived views.
*   Summarized the **Data Persistence(Storage)** behavior to reassure users about data safety.
*   Updated the **Command Summary** table to include v2.0 features.

### 4. Contributions to the Developer Guide (DG)
*   Wrote the implementation details for the **Archive Student Feature**.
*   Wrote the implementation details for the **Storage Feature**, including design logic for post-command persistence.
*   **UML Diagrams**: Created the **Sequence Diagram for Archiving** and the **Sequence Diagram for Storage Saving**.
*   **Design Considerations**: Documented the trade-offs between "Dual-List Management" vs. "Flag-based Filtering."

### 5. Contributions to Team-Based Tasks
*   **Integration Support**: Refactored teammates' commands (e.g., `ScheduleCommand`, `GradeCommand`) to maintain compatibility with the new `StudentList` API after my major refactoring.
*   **Conflict Resolution**: Took the lead in resolving complex merge conflicts in the `Student` class when Archiving, Grade, and Lesson features overlapped.
*   **Release Management**: Verified the `v1.0` Shadow JAR on different computers.

### 6. Review/Mentoring Contributions
*   Reviewed PRs involving `Grade` and `Lesson` features, providing feedback on Javadoc completeness and the Single Level of Abstraction Principle (SLAP).
*   Assisted teammates in resolving Checkstyle formatting errors (e.g., Indentation and Newline issues) during the CI process.
