##Java Analog Clock Simulation

A lightweight, real-time analog clock built entirely in Java. This project was created to demonstrate custom rendering using Java's Graphics2D library and fundamental Object-Oriented Programming (OOP) concepts, without relying on high-level GUI builders or event-driven timers.

##Features:
Real-Time Animation: Updates continuously using a custom background thread, avoiding standard ActionListener implementations.
Custom Graphics: The clock face and hands are drawn manually using java.awt.Graphics2D with anti-aliasing for smooth rendering.
OOP Architecture: Built with a strict focus on Encapsulation, Inheritance, Polymorphism, and Composition.
Trigonometric Accuracy: Calculates exact hand angles dynamically based on the system's current time.

##Project Structure:
ClockFrame.java - The main application window and entry point.
ClockPanel.java - The controller that manages the animation loop and composes the visuals.
ClockFace.java - Renders the circular boundary and hour tick marks.
ClockHand.java - An abstract base class for the clock hands.
HourHand, MinuteHand, SecondHand - Concrete classes implementing the specific angle calculations and dimensions.

##How to Run:
Run the main file ClockFrame.java.
