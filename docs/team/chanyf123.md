# Chan Yi Feng - Project Portfolio Page

## Overview
TutorSwift is a desktop application used for private tutors to manage their students, schedule, and administrative records efficiently. The user interacts with it using a CLI. It is written in Java, and has about 6kLoC.


### Summary of Contributions

#### Code Contributed: [RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=chanyf123&breakdown=true)

* **Feature 1: Edit Student** 
    * *What it does:* Allows tutors to selectively modify an existing student's details (name, academic level, and/or subject) without needing to recreate the entire profile.
    * *Justification:* Student details often change (e.g., advancing to a new academic level or switching subjects). This command provides a seamless way to update records while retaining their existing grades, fees, and schedules.
    * *Highlights:* This enhancement required designing a flexible parsing mechanism capable of handling any combination of optional prefixes (`n/`, `l/`, `sub/`). To ensure high cohesion and maintain encapsulation, the command logic delegates the actual state update and null-checking directly to the `Student` model, protecting the command layer from exposing the student's internal data structure.
  

* **Feature 2: Schedule Lessons**
    * *What it does:* Assigns a recurring weekly lesson slot (day of the week, start time, and end time) to a specific student in the active list.
    * *Justification:* Accurately tracking timetables is critical for tutors. This ensures they know exactly when a lesson occurs and acts as the foundation for the application's schedule-tracking features.
    * *Highlights:* The technical challenge involved implementing robust time conflict validation to prevent double-booking. It required integrating Java's `LocalTime` and `DayOfWeek` APIs to build an internal `Lesson#isOverlapping()` logic. This logic accurately detects time interval collisions strictly for the specified student, while explicitly permitting overlaps across *different* students to allow for group-tuition scenarios.

* **Feature 3: Upcoming Lessons List**
    * *What it does:* Displays a dynamically sorted list of all scheduled lessons across all students for the next 7 days, ordered chronologically.
    * *Justification:* Provides the tutor with an instant, consolidated view of their immediate teaching schedule, eliminating the need to manually inspect each individual student's profile to figure out who they are teaching next.
    * *Highlights:* This was the most complex logic I implemented, requiring real-time contextual calculations. I engineered a `RelativeLesson` wrapper class that calculates the chronological distance (`daysFromToday`) between a lesson's recurring day and the current system time. It handles edge cases such as rolling over a lesson to the following week if its start time has already passed on the current day before sorting the entire list using a custom multi-level comparator.

* **Contributions to the User Guide (UG):**
    * Added documentation for the features `edit`, `schedule`, and `upcoming`.
    * Standardised the formatting for the Command Summary table to improve readability.

* **Contributions to the Developer Guide (DG):**
    * Authored the implementation details and design considerations for the `edit`, `schedule` and `upcoming` feature.
    * Authored the Table of contents and Appendix (project scope, user stories, non-functional requirements, glossary and manual testing instructions)
    * Added the following UML diagrams:
        * Sequence Diagram for `edit`, `schedule` and `upcoming` execution.

* **Contributions to Team-Based Tasks:**
    * Managed the issue tracker, release management.
    * Authored the initial skeleton code for the `Parser` and `StudentList` components.
    * Idea generation for commands like `grade`, `fee`, `archive`.

* **Review/Mentoring Contributions:**
    * Reviewed and approved Pull Requests. (e.g., [PR #37](https://github.com/AY2526S2-CS2113-W11-1/tp/pull/37), [PR #44](https://github.com/AY2526S2-CS2113-W11-1/tp/pull/44))
