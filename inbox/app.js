// Sample data for requests
const requests = {
    incoming: [
        {
            id: 1,
            userName: "Alex Johnson",
            offeredSkill: "Graphic Design",
            wantedSkill: "Spanish Lessons",
            status: "pending"
        },
        {
            id: 2,
            userName: "Sam Wilson",
            offeredSkill: "Web Development",
            wantedSkill: "Photography",
            status: "pending"
        },
        {
            id: 3,
            userName: "Taylor Smith",
            offeredSkill: "Cooking Lessons",
            wantedSkill: "Yoga Instruction",
            status: "accepted"
        }
    ],
    outgoing: [
        {
            id: 101,
            userName: "Maria Garcia",
            offeredSkill: "Yoga Instruction",
            wantedSkill: "Cooking Lessons",
            status: "pending"
        },
        {
            id: 102,
            userName: "Jamie Lee",
            offeredSkill: "Spanish Lessons",
            wantedSkill: "Graphic Design",
            status: "accepted"
        },
        {
            id: 103,
            userName: "Chris Brown",
            offeredSkill: "Photography",
            wantedSkill: "Web Development",
            status: "rejected"
        }
    ]
};

// DOM elements
const incomingRequestsList = document.getElementById('incoming-requests');
const outgoingRequestsList = document.getElementById('outgoing-requests');
const incomingCountBadge = document.getElementById('incoming-count');

// Function to render requests
function renderRequests() {
    // Clear existing lists
    incomingRequestsList.innerHTML = '';
    outgoingRequestsList.innerHTML = '';
    
    // Count pending incoming requests
    const pendingIncomingCount = requests.incoming.filter(req => req.status === 'pending').length;
    incomingCountBadge.textContent = `${pendingIncomingCount} new`;
    
    // Render incoming requests
    if (requests.incoming.length === 0) {
        incomingRequestsList.innerHTML = '<div class="empty-message">No incoming requests</div>';
    } else {
        requests.incoming.forEach(request => {
            const listItem = createRequestElement(request, true);
            incomingRequestsList.appendChild(listItem);
        });
    }
    
    // Render outgoing requests
    if (requests.outgoing.length === 0) {
        outgoingRequestsList.innerHTML = '<div class="empty-message">No outgoing requests</div>';
    } else {
        requests.outgoing.forEach(request => {
            const listItem = createRequestElement(request, false);
            outgoingRequestsList.appendChild(listItem);
        });
    }
}

// Function to create a request list item
function createRequestElement(request, isIncoming) {
    const listItem = document.createElement('li');
    listItem.className = 'request-item';
    listItem.dataset.id = request.id;
    
    const statusClass = `status-${request.status}`;
    const statusText = request.status.charAt(0).toUpperCase() + request.status.slice(1);
    
    listItem.innerHTML = `
        <div class="request-info">
            <div class="user-name">${request.userName}</div>
            <div class="skill-pair">
                <span class="skill-offered">Offers: ${request.offeredSkill}</span>
                <span class="skill-wanted">Wants: ${request.wantedSkill}</span>
            </div>
            <div class="request-status ${statusClass}">${statusText}</div>
        </div>
        <div class="action-buttons">
            ${isIncoming && request.status === 'pending' ? 
                `<button class="btn btn-accept" data-id="${request.id}">✅ Accept</button>
                 <button class="btn btn-reject" data-id="${request.id}">❌ Reject</button>` : ''}
            ${!isIncoming && request.status === 'pending' ? 
                `<button class="btn btn-delete" data-id="${request.id}">❌ Delete</button>` : ''}
        </div>
    `;
    
    return listItem;
}

// Function to handle request actions
function handleRequestAction(type, id, action) {
    if (type === 'incoming') {
        const requestIndex = requests.incoming.findIndex(req => req.id === id);
        if (requestIndex !== -1) {
            if (action === 'accept' || action === 'reject') {
                requests.incoming[requestIndex].status = action === 'accept' ? 'accepted' : 'rejected';
            }
        }
    } else if (type === 'outgoing') {
        const requestIndex = requests.outgoing.findIndex(req => req.id === id);
        if (requestIndex !== -1 && action === 'delete') {
            requests.outgoing.splice(requestIndex, 1);
        }
    }
    
    renderRequests();
}

// Event delegation for button clicks
document.addEventListener('click', function(e) {
    if (e.target.classList.contains('btn-accept')) {
        const requestId = parseInt(e.target.dataset.id);
        handleRequestAction('incoming', requestId, 'accept');
    } else if (e.target.classList.contains('btn-reject')) {
        const requestId = parseInt(e.target.dataset.id);
        handleRequestAction('incoming', requestId, 'reject');
    } else if (e.target.classList.contains('btn-delete')) {
        const requestId = parseInt(e.target.dataset.id);
        handleRequestAction('outgoing', requestId, 'delete');
    }
});

// Initialize the page
document.addEventListener('DOMContentLoaded', renderRequests);