document.addEventListener('DOMContentLoaded', function() {
    // DOM Elements
    const modalOverlay = document.getElementById('modal-overlay');
    const swapModal = document.getElementById('swap-modal');
    const closeModal = document.getElementById('close-modal');
    const openModal = document.getElementById('open-modal');
    const swapForm = document.getElementById('swap-request-form');
    
    // For demo purposes - this would normally be set when clicking "Request Swap" on a profile
    openModal.addEventListener('click', openModalHandler);
    
    // Close modal when clicking X, overlay, or pressing Escape
    closeModal.addEventListener('click', closeModalHandler);
    modalOverlay.addEventListener('click', closeModalHandler);
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape' && !swapModal.classList.contains('hidden')) {
            closeModalHandler();
        }
    });
    
    // Form submission
    swapForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        // Get form values
        const recipient = document.getElementById('recipient').value;
        const wantedSkill = document.getElementById('wanted-skill').value;
        const offeredSkill = document.getElementById('offered-skill').value;
        const message = document.getElementById('message').value;
        
        // Validate
        if (!wantedSkill || !offeredSkill) {
            alert('Please select both skills to proceed');
            return;
        }
        
        // In a real app, you would send this data to your backend
        console.log('Swap Request Submitted:', {
            recipient,
            wantedSkill,
            offeredSkill,
            message
        });
        
        // Show confirmation and close modal
        alert(`Swap request sent to ${recipient}!`);
        closeModalHandler();
        swapForm.reset();
    });
    
    // Modal control functions
    function openModalHandler() {
        modalOverlay.classList.remove('hidden');
        swapModal.classList.remove('hidden');
        document.body.style.overflow = 'hidden'; // Prevent scrolling
    }
    
    function closeModalHandler() {
        modalOverlay.classList.add('hidden');
        swapModal.classList.add('hidden');
        document.body.style.overflow = ''; // Re-enable scrolling
    }
    
    // For demo - auto-open the modal (remove in production)
    openModal.click();
});