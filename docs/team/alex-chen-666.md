# Chen Zhendong's Project Portfolio Page

## Overview

TutorSwift is a CLI-based desktop application for private tutors to manage students, lessons, and payments. It streamlines administrative tasks through a fast, text-based interface optimized for efficient data entry.

Given below are my contributions to the project.

## Summary of Contributions

### 1. Code Contributed
[Link to my code on the tP Code Dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=alex-chen-666&breakdown=true)

### 2. Enhancements Implemented

#### Major Enhancement: Core Student Management (Add Command)
*   **What it is**: Developed the primary mechanism for registering students with structured attributes.
*   **Features**: Implemented the `add` command supporting mandatory prefixes (`n/`, `l/`, `sub/`).
*   **Justification**: This is the foundation of TutorSwift; all subsequent features like grade tracking and scheduling depend on the accurate entry of this data.
*   **Technical Depth**:
    *   **Flexible Parsing**: Integrated custom prefix-extraction logic to allow parameters in any order, enhancing CLI usability.
    *   **Data Integrity**: Implemented strict defensive checks to prevent duplicate student names.
    *   **Defensive Design**: Utilized a combination of `TutorSwiftException` for user errors and `assert` statements to verify internal logic during instantiation.

#### Major Enhancement: Student Archiving System
*   **What it is**: Introduced a dual-list architecture to separate active students from historical records.
*   **Features**: Implemented `archive`, `unarchive`, `list-archive`, and `delete-archive` commands.
*   **Justification**: Tutors need to preserve the history of graduated students without cluttering their primary workspace.
*   **Technical Depth**:
    *   **List Refactoring**: Refactored `StudentList` to manage two internal `ArrayList` objects efficiently.
    *   **Index Validation**: Ensured data safety by validating student indices across distinct lists.
    *   **Comprehensive Retrieval**: Upgraded the `find` command to search across both active and archived repositories simultaneously.

#### Major Enhancement: Persistent Local Storage
*   **What it is**: Developed the `Storage` component for automatic data persistence to `./data/tutorswift.txt`.
*   **Justification**: Ensures a seamless experience where data is preserved automatically even if the application closes unexpectedly.
*   **Technical Depth**:
    *   **Nested Data Encoding**: Designed a pipe-separated format capable of storing complex attributes like grades and fee records.
    *   **Robustness**: Implemented a "skip-and-log" mechanism to handle corrupted data lines without crashing the application.
    *   **Automation**: Added automatic directory and file creation logic upon startup.

#### Minor Enhancement: System Logging and UX Optimization
*   Integrated `java.util.logging` and configured a custom filter to suppress system `INFO` red-text from the console, providing a cleaner CLI experience while maintaining diagnostic records.

### 3. Contributions to the User Guide (UG)
*   Documented the **Add Student** and **Archiving System** features.
*   Summarized **Data Persistence** behavior and safety.
*   Standardized the **Command Summary** table for v2.0.

### 4. Contributions to the Developer Guide (DG)
*   Documented implementation details for **Archive** and **Storage** features.
*   Created **Sequence Diagrams** for the archiving operation and automatic saving logic.
*   Authored design considerations regarding **Dual-List Management**.

### 5. Contributions to Team-Based Tasks
*   **Refactoring Support**: Updated teammates' commands to match the new `StudentList` API.
*   **Conflict Resolution**: Led the resolution of complex merge conflicts in the `Student` class.
*   **Release Management**: Verified Shadow JAR compatibility across different computers.

### 6. Review/Mentoring Contributions
*   Reviewed PRs, focusing on SLAP and Javadoc completeness.
*   Assisted teammates in resolving **Checkstyle** errors and CI pipeline failures.
