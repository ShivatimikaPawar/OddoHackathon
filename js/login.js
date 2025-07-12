document.addEventListener('DOMContentLoaded', function() {
  const profileUpload = document.getElementById('profile-upload');
  const profileImage = document.getElementById('profile-image');
  const defaultIcon = document.getElementById('default-icon');
  const MAX_SIZE = 2 * 1024 * 1024; // 2MB

  // Click handler
  document.querySelector('.profile-icon-container').addEventListener('click', function() {
    profileUpload.click();
  });
  
  // File selection handler
  profileUpload.addEventListener('change', function(e) {
    const file = e.target.files[0];
    if (!file) return;
    
    // Validate file type
    if (!file.type.match('image.*')) {
      alert('Please select an image file (JPEG, PNG, etc.)');
      return;
    }
    
    // Validate file size
    if (file.size > MAX_SIZE) {
      alert('Image too large (max 2MB)');
      return;
    }
    
    const reader = new FileReader();
    
    reader.onloadstart = function() {
      // Show loading indicator if needed
    };
    
    reader.onload = function(event) {
      profileImage.src = event.target.result;
      profileImage.style.display = 'block';
      defaultIcon.style.display = 'none';
      
      // Here you would typically send the image to your server
      // uploadProfileImage(file);
    };
    
    reader.onerror = function() {
      console.error('Error reading file');
      alert('Error reading image file');
    };
    
    reader.onloadend = function() {
      // Hide loading indicator if needed
    };
    
    reader.readAsDataURL(file);
  });
  
  // Load saved image if available
  // loadSavedProfile();
});