import cv2
import numpy as np

# Load and preprocess the iris image
def preprocess_iris(image_path):
    image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)
    image = cv2.resize(image, (64, 64))  # Resize for consistency
    return image

# Extract SIFT keypoints and descriptors
def extract_features(image):
    sift = cv2.SIFT_create()
    keypoints, descriptors = sift.detectAndCompute(image, None)
    return keypoints, descriptors

# Compare two iris images using SIFT feature matching
def compare_iris(image1_path, image2_path):
    image1 = preprocess_iris(image1_path)
    image2 = preprocess_iris(image2_path)

    keypoints1, descriptors1 = extract_features(image1)
    keypoints2, descriptors2 = extract_features(image2)

    # Use FLANN-based matcher
    index_params = dict(algorithm=1, trees=5)
    search_params = dict(checks=50)
    flann = cv2.FlannBasedMatcher(index_params, search_params)
    matches = flann.knnMatch(descriptors1, descriptors2, k=2)

    # Apply Lowe’s ratio test
    good_matches = [m for m, n in matches if m.distance < 0.9 * n.distance]

    similarity_score = len(good_matches) / min(len(keypoints1), len(keypoints2))

    print(f'similarity score: {similarity_score * 100}%')

    return similarity_score > 0.6  # Adjust threshold as needed

# Example Usage
reference_iris = "iris_data/aeval1.bmp"
test_iris = "iris_data/aeval3.bmp"

if compare_iris(reference_iris, test_iris):
    print("Iris Matched!")
else:
    print("Iris NOT Matched!")
