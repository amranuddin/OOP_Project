# <p align="center" style="color:#007acc; font-family:sans-serif;">🏥 Hospital Management Mini System (OOP Academic Project)</p>

<p align="center">
  👉 <strong>Live Demo Website:</strong> <a href="https://hms-kitten-5b6d93.netlify.app/">https://hms-kitten-5b6d93.netlifyapp/</a>
</p>

An interactive, web-hosted **Hospital Management Mini System** designed to streamline core healthcare administrative workflows. Developed as an academic project, this system implements **Object-Oriented Programming (OOP)** principles and follows the industry-standard **Model-View-Controller (MVC)** architectural design pattern.

---
  
## <span style="color:#ff5722;">🛠️ OOP Implementation in This Project</span>

This project serves as a practical demonstration of the four core pillars of Object-Oriented Programming:

1. **Encapsulation:** 
   * Patient and Doctor data attributes are set to `private` (e.g., `private String name`) to restrict unauthorized access. 
   * Safe data access and modifications are strictly governed via public **Getter** methods.

2. **Inheritance:** 
   * The main UI controller class extends Java's built-in framework (`public class Project extends JFrame`), inheriting window creation and layout capabilities.

3. **Polymorphism:** 
   * Method Overriding is implemented by custom-defining the standard `toString()` method in the `Patient` and `Doctor` models. This allows dynamic and structured data display inside selection dropdowns (`JComboBox`).

4. **Abstraction:** 
   * The visual interface (View Layer) remains completely isolated from the business logic processing. The UI simply calls high-level abstract logic like `system.addPatient(...)` without worrying about how data is mapped or maintained in memory.

---

## <span style="color:#2196f3;">🎯 Core Feature Modules</span>

### 1. Patient Registration Module
* **Data Collection:** Registers patient details including Name, Age, and active Medical Symptoms/Diseases.
* **Input Validation:** Built-in exception handling (capturing `NumberFormatException`) prevents empty submissions and ensures proper numeric age format.
* **Live Directory Sync:** Newly registered patient records instantly update the data grid table (`JTable`) and update the appointment selection dropdowns dynamically.

### 2. Doctor Appointment Scheduler
* **Dynamic Dependency Sync:** Automatically fetches registered patients from memory to ensure appointments are only booked for registered individuals.
* **Consultant Portfolio:** Pre-loaded medical doctor profiles categorized by specialties (Cardiologist, Pediatrician, Neurologist) and chamber room numbers.
* **Digital Confirmation Voucher:** Generates an instant, professional appointment confirmation slip using modal dialogues upon submission.

---

## <span style="color:#4caf50;">🏗️ Architectural Pattern (MVC)</span>

The codebase strictly adheres to the **Separation of Concerns (SoC)** principle:
* **Model (`Patient`, `Doctor`):** Blueprints holding state parameters and getter interfaces. They have zero dependencies on the GUI libraries.
* **Controller/Core Engine (`HospitalSystem`):** Handles runtime state memory utilizing dynamic `ArrayList` collections.
* **View Driver (`Project` / UI Component):** Orchestrates structural element placement using nested layout managers to ensure display stability.

---

## <span style="color:#9c27b0;">⚙️ Technical Specifications</span>

* **Design Pattern:** Model-View-Controller (MVC) & OOP
* **Data Structures:** Dynamic Safe Collections (`ArrayList<E>`)
* **Deployment:** Hosted live on Netlify

---

