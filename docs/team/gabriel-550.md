# Lim Swee How Gabriel's Project Portfolio Page

## Overview

TutorSwift is a command-line application for private tutors to manage student records, including lessons, grades, and payments.

It is designed to streamline administrative tasks such as tracking student performance and managing finances efficiently through a CLI interface.

---

### Summary of Contributions

---

### Code contributed

[RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=gabriel-550&breakdown=true)

---

### Enhancements implemented

**1. Delete Feature**

- Implemented the `delete` command to permanently remove a student from the active student list.

- What it does: Deletes the student at the specified index from the active list and confirms the removal with the student's details and updated student count.

- Justification: This feature improves the product significantly because it provides a clean way to remove students the user no longer teach, keeping the active list accurate and up to date.

- Highlights: Required careful index validation (1-based to 0-based conversion) and clear error messaging when an out-of-range index is given.

**2. Finance Tracking Features**

- Implemented the `fee`, `paid`, and `unpaid` commands to give the user a complete payment tracking system for each student.

- **`fee` command**
    - What it does: Sets a per-lesson fee in dollars for a specific student. The fee is stored in the student's `FeeRecord` and displayed alongside their profile in the list.
    - Justification: This feature improves the product significantly because tutors charge different rates for different students. Having the fee stored per student makes it easy to track and review.

- **`paid` command**
    - What it does: Marks a student's payment as paid for a specified month using the `ym/YYYY-MM` format. Multiple months can be tracked independently and all paid months are displayed together on the student's profile.
    - Justification: This feature improves the product significantly because tutors are paid monthly and need a simple way to record which months have been paid.

- **`unpaid` command**
    - What it does: Marks a student's payment as unpaid for a specified month using the `ym/YYYY-MM` format, removing it from the student's paid records. Other paid months are unaffected.
    - Justification: This feature improves the product significantly because tutors may mark the wrong month as paid and need a way to correct it cleanly without affecting other records.

- Highlights: Implemented the `FeeRecord` class to encapsulate all finance-related data (per-lesson fee and list of paid months).

---

### Contributions to the User Guide

- Wrote documentation for the `delete`, `fee`, `paid` and `unpaid` commands, including format, examples of usage and expected behaviour.

- Filled in the FAQ section with answer covering common user concern such as payment display question.

- Updated the Command Summary table with the commands I have implemented.

- Reviewed existing UG sections written by other group members for accuracy and consistency.

---

### Contributions to the Developer Guide

- Documented the implementation details of the `delete`, `fee`, `paid` and `unpaid` features.

- Added sequence diagrams for each feature to show interactions between system components and the operations.

- Created example usage scenarios and documented design considerations.

---

### Contributions to team-based tasks

- Helped test features across the team and flagged edge cases (e.g. out-of-range index handling).

- Contributed to feature planning and team discussions.

---

### Review/mentoring contributions

- Detected and resolved issues in condition checks and test cases.

- Reviewed team members’ pull requests and provided comments.
