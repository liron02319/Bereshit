import pandas as pd
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
import time

# Load the CSV file
csv_path = r"C:\Users\User\Desktop\לירון\AutoSpace\Ex0_Bereshit\simulation_results.csv"
df = pd.read_csv(csv_path)

# Strip any leading/trailing spaces from the column names
df.columns = df.columns.str.strip()

# Print column names to verify they match the expected ones
print("Columns in the CSV:", df.columns)

# Make sure the column names are correct ('alt', 'vs', 'hs')
# Adjust this part according to your CSV column names if necessary
if 'alt' not in df.columns or 'vs' not in df.columns or 'hs' not in df.columns:
    print("Error: Required columns are missing.")
    print("Available columns:", df.columns)
    exit()

# Initial position and speed
y_position = df['alt'][0]  # Initial altitude
vertical_speed = df['vs'][0]  # Initial vertical speed
horizontal_speed = df['hs'][0]  # Initial horizontal speed

# Create a 3D plot
fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

# Dynamically adjust the plot limits based on the altitude data
z_min = df['alt'].min()
z_max = df['alt'].max()
ax.set_xlim([0, 1000])  # Adjust X-axis as needed
ax.set_ylim([0, 1000])  # Adjust Y-axis as needed
ax.set_zlim([z_min, z_max])  # Adjust Z-axis based on the altitude range

# Set labels
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('Altitude')

# Plot the initial position
spacecraft, = ax.plot([], [], [], 'bo', markersize=10)  # 'bo' means blue circle

# Simulate the landing in real-time
for frame in range(1, len(df)):  # Loop through the CSV rows
    # Get the current position and speed from the CSV
    y_position = df['alt'][frame]
    vertical_speed = df['vs'][frame]
    horizontal_speed = df['hs'][frame]

    # Update the position of the spacecraft
    # Assuming horizontal movement is based on speed and frame number
    x_position = frame * horizontal_speed * 0.1  # Adjust scaling as needed
    z_position = y_position  # Altitude directly from CSV

    # Update the plot with the new position
    spacecraft.set_data(x_position, x_position)  # Update X and Y data
    spacecraft.set_3d_properties(z_position)  # Update Z (altitude)

    # Redraw the plot
    plt.draw()
    plt.pause(0.05)  # Adjust this for the speed of the animation



# Keep the plot open at the end
plt.show()