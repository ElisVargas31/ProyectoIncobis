window.onload = function() {
    document.getElementById('area-aseo').addEventListener('change', function() {
        var comentarioDiv = document.getElementById('comentarioArea');
        if (this.value === 'otros') {
            comentarioDiv.style.display = 'block';
        } else {
            comentarioDiv.style.display = 'none';
        }
    });
};
