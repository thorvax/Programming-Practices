function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
async function demo() {
    console.log("Before sleep");
    await sleep(2000); // Wait for 2 seconds
    console.log("After sleep");
}


function toggleCursorRight() {
    var area = document.getElementById("Right booby");
    if (area.style.cursor === 'grab') {
         area.style.cursor = 'grabbing';
        // demo();
        // area.style.cursor = 'grab';
    } else {
        area.style.cursor = 'grab';
    }
    // alert("You are not allowed to touch my right boob, only the left one is allowed to be touched.");
}
function toggleCursorLeft() {
    var area = document.getElementById('Left booby');
    if (area.style.cursor === 'grab') {
        area.style.cursor = 'grabbing';
        // demo();
        // area.style.cursor = 'grab';
    
    } else {
        area.style.cursor = 'grab';
    }
    // alert("You are not allowed to touch my left boob, only the right one is allowed to be touched.");
}


