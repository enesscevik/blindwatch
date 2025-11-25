**BlindWatch** is a minimalist application developed for Wear OS (Android Smartwatch) that enables users to tell the time via **tactile Morse-like code vibrations**, eliminating the need to look at the screen. It is designed specifically for visually impaired users or those seeking non-distracting timekeeping.

## ✨ Key Features

* **Tactile Time Notification:** The app communicates time using a simplified 12-hour clock system, conveyed through Morse-like code vibrations.
* **Modern Development:** Targets the Wear OS 5 (or later) platform, built with Jetpack Compose for Wear OS.
* **High Performance:** Utilizes hardware-accelerated vibration APIs (`VibratorManager` / `VibrationEffect`) for low-latency, distinct haptic feedback.

## 🛠️ Developer Guide

### Requirements

* **Android Studio:** Jellyfish (or later)
* **Kotlin:** 1.9 (or later)
* **Android SDK:** Minimum Version 30+ (Wear OS 3.0 and above), Target Version 34 (Wear OS 5)

### Opening and Running the Project

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/enesscevik/blindwatch.git](https://github.com/enesscevik/blindwatch.git)
    ```
2.  **Open in Android Studio:** Launch Android Studio and open the cloned `blindwatch` folder via `File > Open`.
3.  **Gradle Sync:** Wait for the project to sync automatically.
4.  **Run the Application:**
    * Launch a **Wear OS 3.0+ Emulator** OR ensure your physical watch is connected via **ADB**.
    * Select your target device from the dropdown list in the top menu and press the **Run** button.

### Vibration Mechanism

The application utilizes the modern **`VibratorManager`** over older `Vibrator` APIs for generating patterns.

| Digit | Morse-like Code | Vibration Pattern |
| :---: | :-------: | :---: |
| **1** | .     | Dot |
| **2** | ..    | Dot, Dot |
| **5** | ..... | Five Dots |
| **6** | -     | Dash |
| **7** | -     | Dash, Dash |
| **0** | ----- | Five Dashes |
| **10** | .-.   | Dot, Dash, Dot |
| **11** | -.-   | Dash, Dot, Dash |

**Timing (Optimal Suggested Durations):**
* **Dot:** 60/100 ms Vibration
* **Dash:** 180/300 ms Vibration
* **Intra-Character Space:** 60/150 ms Pause (Pause between dots/dashes within the same digit)

---

## 🤝 Contributing

Feedback and contributions are welcome! Please feel free to open an **Issue** or submit a **Pull Request**.

**License:** [MIT]
