import pandas as pd
import matplotlib.pyplot as plt
from tkinter import Tk, filedialog

# Optional: Allow user to choose CSV file via file dialog
# Tk().withdraw()
# csv_path = filedialog.askopenfilename(title="Select CSV File", filetypes=[("CSV files", "*.csv")])

# Or use a fixed path:
csv_path = r"C:\Users\User\Desktop\לירון\AutoSpace\Ex0_Bereshit\simulation_results.csv"

# Read the CSV into a DataFrame
df = pd.read_csv(csv_path)

# Normalize column names: strip spaces and make lowercase
df.columns = df.columns.str.strip().str.lower()

# Print available columns for debug
print("CSV columns:", df.columns.tolist())

# --- Show Altitude ---
plt.figure()
plt.plot(df["time"], df["alt"], label="Altitude (m)", color="blue")
plt.title("Altitude Over Time")
plt.xlabel("Time (s)")
plt.ylabel("Altitude (m)")
plt.grid(True)
plt.legend()
plt.show()

# --- Show Vertical Speed ---
plt.figure()
plt.plot(df["time"], df["vs"], label="Vertical Speed (m/s)", color="red")
plt.title("vs")
plt.xlabel("Time (s)")
plt.ylabel("vs Speed (m/s)")
plt.grid(True)
plt.legend()
plt.show()

# --- Show Vertical Speed ---
plt.figure()
plt.plot(df["time"], df["hs"], label="Horizontal Speed (m/s)", color="red")
plt.title("hs")
plt.xlabel("Time (s)")
plt.ylabel("hs Speed (m/s)")
plt.grid(True)
plt.legend()
plt.show()

# --- Show Fuel ---
plt.figure()
plt.plot(df["time"], df["fuel"], label="Fuel (L)", color="green")
plt.title("Fuel")
plt.xlabel("Time (s)")
plt.ylabel("Fuel (L)")
plt.grid(True)
plt.legend()
plt.show()