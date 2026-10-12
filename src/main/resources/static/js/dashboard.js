/**
 * AssetOS - IT Asset Management System
 * Dashboard Client-side Interactivity & Theme Manager
 */

// Immediate theme bootstrap to avoid unstyled theme flash
(function() {
    try {
        const saved = localStorage.getItem('assetos_accent_theme') || 'classic-charcoal';
        document.documentElement.setAttribute('data-theme', saved);
        if (document.body) document.body.setAttribute('data-theme', saved);
    } catch(e) {}
})();

window.setAssetOSTheme = function(themeName) {
    if (!themeName) return;
    const key = themeName.toLowerCase().trim().replace(/\s+/g, '-');
    try {
        localStorage.setItem('assetos_accent_theme', key);
        document.documentElement.setAttribute('data-theme', key);
        if (document.body) document.body.setAttribute('data-theme', key);
    } catch(e) {}
};

// Auto-load SheetJS for authentic .xlsx binary generation and parsing
(function initXLSX() {
    if (!window.XLSX && !document.getElementById('sheetjs-script')) {
        const s = document.createElement('script');
        s.id = 'sheetjs-script';
        s.src = '/js/xlsx.full.min.js';
        document.head.appendChild(s);
    }
})();

/**
 * Universal Excel Exporter for AssetOS
 * Generates true .xlsx files using SheetJS when available, with UTF-8 BOM CSV fallback.
 */
window.exportTableToExcel = function(tableOrId, filename, excludeLastActionCol) {
    if (excludeLastActionCol === undefined) excludeLastActionCol = true;
    let table = typeof tableOrId === 'string' ? document.getElementById(tableOrId) : tableOrId;
    if (!table) {
        table = document.querySelector('.black-table, .bordered-table, .black-report-table, .black-return-table, table');
    }
    if (!table) {
        alert('No table data found to export.');
        return;
    }

    const allRows = table.querySelectorAll('tr');
    const tableData = [];

    allRows.forEach(tr => {
        if (tr.style.display === 'none') return;
        const cells = tr.querySelectorAll('th, td');
        if (cells.length === 0) return;

        let limit = cells.length;
        if (excludeLastActionCol && cells.length > 1) {
            const lastText = (cells[cells.length - 1].innerText || '').trim().toUpperCase();
            const hasAction = lastText.includes('ACTION') || cells[cells.length - 1].querySelector('button, form, .action-btn, .action-icon-btn, .emp-card-actions');
            if (hasAction) limit = cells.length - 1;
        }

        const row = [];
        for (let i = 0; i < limit; i++) {
            let text = cells[i].innerText || cells[i].textContent || '';
            text = text.replace(/[\r\n\t]+/g, ' ').trim();
            row.push(text);
        }
        if (row.length > 0) tableData.push(row);
    });

    if (tableData.length === 0) {
        alert('No rows available to export.');
        return;
    }

    const dateStr = new Date().toISOString().split('T')[0];
    const baseName = (filename || 'AssetOS_Export') + '_' + dateStr;

    // 1. Try real .xlsx binary export if XLSX library is loaded
    if (window.XLSX) {
        try {
            const ws = XLSX.utils.aoa_to_sheet(tableData);
            const wb = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(wb, ws, "Records");
            XLSX.writeFile(wb, baseName + '.xlsx');
            return;
        } catch(err) {
            console.warn('XLSX writing error, using CSV fallback', err);
        }
    }

    // 2. High-compatibility UTF-8 BOM CSV Fallback
    const csv = tableData.map(r => r.map(c => '"' + c.replace(/"/g, '""') + '"').join(',')).join('\r\n');
    const blob = new Blob(['\uFEFF' + csv], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.setAttribute('download', baseName + '.csv');
    document.body.appendChild(link);
    link.click();
    setTimeout(() => {
        if (link.parentNode) link.parentNode.removeChild(link);
        URL.revokeObjectURL(link.href);
    }, 200);
};

/**
 * Special Employee Grid to Excel Exporter (.xlsx)
 */
window.exportEmployeesToExcel = function() {
    const cards = document.querySelectorAll('.emp-profile-card');
    if (!cards || cards.length === 0) {
        const table = document.getElementById('table-employee') || document.querySelector('table');
        if (table) return window.exportTableToExcel(table, 'AssetOS_Employees', true);
        alert('No employee data found to export.');
        return;
    }

    const rows = [
        ["EMPLOYEE ID", "NAME", "STATUS", "DESIGNATION", "DEPARTMENT", "BRANCH", "MOBILE", "EMAIL"]
    ];

    cards.forEach(card => {
        if (card.style.display === 'none') return;
        const name = (card.querySelector('.emp-name-title')?.innerText || '').trim();
        const empId = (card.querySelector('.emp-id-sub')?.innerText || '').trim();
        const status = (card.querySelector('.status-text-pill')?.innerText || '').trim();

        let desig = '', dept = '', branch = '', mobile = '', email = '';
        const metaGrid = card.querySelector('.emp-meta-grid');
        if (metaGrid) {
            const labels = metaGrid.querySelectorAll('.label');
            const vals = metaGrid.querySelectorAll('.val');
            for (let i = 0; i < labels.length; i++) {
                const label = (labels[i].innerText || '').toLowerCase();
                const val = (vals[i]?.innerText || '').trim();
                if (label.includes('designation')) desig = val;
                else if (label.includes('department')) dept = val;
                else if (label.includes('branch')) branch = val;
                else if (label.includes('mobile')) mobile = val;
                else if (label.includes('email')) email = val;
            }
        }
        rows.push([empId, name, status, desig, dept, branch, mobile, email]);
    });

    const dateStr = new Date().toISOString().split('T')[0];
    if (window.XLSX) {
        try {
            const ws = XLSX.utils.aoa_to_sheet(rows);
            const wb = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(wb, ws, "Employees");
            XLSX.writeFile(wb, 'AssetOS_Employees_' + dateStr + '.xlsx');
            return;
        } catch(e) {}
    }

    // CSV Fallback
    const csv = rows.map(r => r.map(c => '"' + (c || '').replace(/"/g, '""') + '"').join(',')).join('\r\n');
    const blob = new Blob(['\uFEFF' + csv], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.setAttribute('download', 'AssetOS_Employees_' + dateStr + '.csv');
    document.body.appendChild(link);
    link.click();
    setTimeout(() => {
        if (link.parentNode) link.parentNode.removeChild(link);
        URL.revokeObjectURL(link.href);
    }, 200);
};

/**
 * Universal Assets Inventory Exporter
 */
window.exportAssetsExcel = function() {
    const table = document.getElementById('assetsMasterTable') || document.querySelector('.bordered-table');
    if (table) {
        window.exportTableToExcel(table, 'AssetOS_Inventory', true);
    } else {
        alert('Asset inventory table not found.');
    }
};

// -------------------------------------------------------------
// Bulk Excel Import System (Functional implementation)
// -------------------------------------------------------------
window.downloadExcelTemplate = function(type) {
    if (!window.XLSX) {
        alert('Excel processor is loading. Please try again in a moment.');
        return;
    }
    let headers = [], sampleRows = [], filename = '';
    if (type === 'assets') {
        headers = ["Asset Tag", "Name / Model", "Category", "Brand", "Serial Number", "Assigned To", "Status", "Condition", "Purchase Date", "Warranty"];
        sampleRows = [
            ["AST0091", "MacBook Air M2 13-inch", "Laptop", "Apple", "SN-MBA-2026-91", "Farhan Ahmed", "Assigned", "New", "2026-05-10", "3 Years"],
            ["AST0092", "Dell UltraSharp U2723QE", "Peripheral", "Dell", "SN-MON-2026-92", "Unassigned", "Available", "Good", "2026-06-15", "2 Years"],
            ["AST0093", "HP LaserJet Pro M404dn", "Printer", "HP", "SN-PRN-2026-93", "Inventory Pool", "Available", "Good", "2026-07-01", "1 Year"]
        ];
        filename = "AssetOS_Asset_Import_Template.xlsx";
    } else {
        headers = ["Employee ID", "Full Name", "Status", "Designation", "Department", "Branch", "Mobile", "Email"];
        sampleRows = [
            ["EMP0081", "Arun Verma", "Active", "Senior DevOps Engineer", "IT", "Dhaka", "9876543210", "arun.v@company.com"],
            ["EMP0082", "Priya Sharma", "Active", "HR Specialist", "HR", "Sylhet", "9876543211", "priya.s@company.com"],
            ["EMP0083", "Rohan Mehta", "Active", "Financial Analyst", "Finance", "Chittagong", "9876543212", "rohan.m@company.com"]
        ];
        filename = "AssetOS_Employee_Import_Template.xlsx";
    }

    const data = [headers, ...sampleRows];
    const ws = XLSX.utils.aoa_to_sheet(data);
    const wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, "Template");
    XLSX.writeFile(wb, filename);
};

window.openExcelImportModal = function(type) {
    type = type || 'assets';
    let modal = document.getElementById('excelImportModal');
    if (!modal) {
        modal = document.createElement('div');
        modal.id = 'excelImportModal';
        modal.className = 'excel-import-modal-overlay';
        modal.innerHTML = `
            <div class="excel-import-modal-box">
                <div class="excel-modal-header">
                    <div>
                        <h3 id="importModalTitle" style="margin:0; font-size:1.15rem; font-weight:700; color:#18181b;">📥 Bulk Import from Excel</h3>
                        <p id="importModalSubtitle" style="margin:0.25rem 0 0; font-size:0.8rem; color:#6b7280;">Upload an .xlsx, .xls, or .csv spreadsheet to import records directly.</p>
                    </div>
                    <button type="button" class="excel-modal-close" onclick="closeExcelImportModal()">&times;</button>
                </div>
                <div class="excel-modal-body">
                    <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:1rem; padding:0.75rem 1rem; background:#fbf9ea; border:1px solid #e7e0b5; border-radius:8px;">
                        <div>
                            <strong style="font-size:0.85rem; color:#854d0e;">Need the formatted spreadsheet template?</strong>
                            <div style="font-size:0.75rem; color:#a16207;">Download pre-built template with headers and sample rows.</div>
                        </div>
                        <button type="button" id="btnDownloadTemplate" class="btn-export-pill" style="background:#fff; border-color:#d4ca8c;">
                            <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="#16a34a" stroke-width="2"><path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path><polyline points="7 10 12 15 17 10"></polyline><line x1="12" y1="15" x2="12" y2="3"></line></svg>
                            <span>Download Template (.xlsx)</span>
                        </button>
                    </div>

                    <div class="excel-drop-zone" id="excelDropZone" onclick="document.getElementById('excelFileInput').click()">
                        <svg width="36" height="36" viewBox="0 0 24 24" fill="none" stroke="#16a34a" stroke-width="1.8" style="margin-bottom:0.5rem;"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path><polyline points="14 2 14 8 20 8"></polyline><line x1="8" y1="13" x2="16" y2="17"></line><line x1="16" y1="13" x2="8" y2="17"></line></svg>
                        <strong style="color:#27272a; font-size:0.92rem;">Click to browse or drag & drop Excel file</strong>
                        <span style="font-size:0.78rem; color:#71717a; margin-top:0.25rem;">Supports Microsoft Excel (.xlsx, .xls) and CSV (.csv)</span>
                        <input type="file" id="excelFileInput" accept=".xlsx,.xls,.csv" style="display:none;" onchange="handleExcelFileUpload(event)">
                    </div>

                    <div id="excelPreviewArea" style="display:none; margin-top:1rem;">
                        <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:0.5rem;">
                            <span id="previewCountBadge" style="font-size:0.82rem; font-weight:700; color:#15803d; background:#dcfce7; padding:0.2rem 0.6rem; border-radius:999px;">0 records parsed</span>
                            <span id="previewFileName" style="font-size:0.78rem; color:#6b7280;"></span>
                        </div>
                        <div style="max-height:180px; overflow-y:auto; border:1px solid #e5e7eb; border-radius:6px;">
                            <table id="excelPreviewTable" style="width:100%; font-size:0.75rem; border-collapse:collapse; text-align:left;">
                                <thead style="background:#171614; color:#fff; position:sticky; top:0;"></thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="excel-modal-footer">
                    <button type="button" class="btn-cancel" onclick="closeExcelImportModal()" style="padding:0.5rem 1rem; border:1px solid #d1d5db; background:#fff; border-radius:6px; cursor:pointer;">Cancel</button>
                    <button type="button" id="btnConfirmImport" class="btn-primary" disabled onclick="executeBulkImport()" style="padding:0.5rem 1.25rem; background:#16a34a; color:#fff; border:none; border-radius:6px; font-weight:600; cursor:pointer; opacity:0.6;">
                        Confirm &amp; Import Records
                    </button>
                </div>
            </div>
        `;
        document.body.appendChild(modal);

        // Add styling for Excel import modal
        const style = document.createElement('style');
        style.innerHTML = `
            .excel-import-modal-overlay {
                position: fixed; inset: 0; background: rgba(0,0,0,0.55);
                display: flex; align-items: center; justify-content: center;
                z-index: 99999; backdrop-filter: blur(2px);
            }
            .excel-import-modal-box {
                background: #ffffff; width: 92%; max-width: 650px;
                border-radius: 12px; box-shadow: 0 20px 25px -5px rgba(0,0,0,0.2);
                overflow: hidden; animation: popIn 0.2s ease;
            }
            .excel-modal-header {
                padding: 1.1rem 1.5rem; border-bottom: 1px solid #e5e7eb;
                display: flex; justify-content: space-between; align-items: flex-start;
            }
            .excel-modal-close {
                background: none; border: none; font-size: 1.5rem;
                line-height: 1; cursor: pointer; color: #9ca3af;
            }
            .excel-modal-body { padding: 1.25rem 1.5rem; }
            .excel-modal-footer {
                padding: 1rem 1.5rem; background: #f9fafb;
                border-top: 1px solid #e5e7eb; display: flex;
                justify-content: flex-end; gap: 0.75rem;
            }
            .excel-drop-zone {
                border: 2px dashed #bbf7d0; background: #f0fdf4;
                border-radius: 10px; padding: 1.5rem 1rem;
                display: flex; flex-direction: column; align-items: center;
                cursor: pointer; transition: all 0.2s ease;
            }
            .excel-drop-zone:hover {
                border-color: #22c55e; background: #dcfce7;
            }
        `;
        document.head.appendChild(style);
    }

    modal.dataset.importType = type;
    document.getElementById('importModalTitle').innerText = '📥 Bulk Import ' + (type === 'assets' ? 'Assets' : 'Employees') + ' from Excel';
    document.getElementById('btnDownloadTemplate').onclick = function() { window.downloadExcelTemplate(type); };
    document.getElementById('excelFileInput').value = '';
    document.getElementById('excelPreviewArea').style.display = 'none';
    const confirmBtn = document.getElementById('btnConfirmImport');
    confirmBtn.disabled = true;
    confirmBtn.style.opacity = '0.6';
    confirmBtn.innerText = 'Confirm & Import Records';
    modal.style.display = 'flex';
};

window.closeExcelImportModal = function() {
    const modal = document.getElementById('excelImportModal');
    if (modal) modal.style.display = 'none';
};

window.parsedExcelRows = [];

window.handleExcelFileUpload = function(e) {
    const file = e.target.files && e.target.files[0];
    if (!file) return;

    document.getElementById('previewFileName').innerText = file.name;
    const reader = new FileReader();

    reader.onload = function(event) {
        try {
            let data = event.target.result;
            if (!window.XLSX) {
                alert('Excel reader component is loading, please try again.');
                return;
            }
            const workbook = XLSX.read(data, { type: 'binary' });
            const sheetName = workbook.SheetNames[0];
            const worksheet = workbook.Sheets[sheetName];
            const jsonRows = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

            if (!jsonRows || jsonRows.length <= 1) {
                alert('The uploaded Excel file does not contain any records.');
                return;
            }

            const headers = jsonRows[0];
            const rows = jsonRows.slice(1).filter(r => r.some(c => c !== undefined && c !== null && String(c).trim() !== ''));

            window.parsedExcelRows = rows.map(r => {
                let obj = {};
                headers.forEach((h, idx) => {
                    if (h) obj[String(h).trim()] = r[idx] !== undefined ? String(r[idx]).trim() : '';
                });
                return obj;
            });

            // Populate preview table
            const previewTable = document.getElementById('excelPreviewTable');
            const thead = previewTable.querySelector('thead');
            const tbody = previewTable.querySelector('tbody');
            thead.innerHTML = '<tr>' + headers.map(h => `<th style="padding:0.4rem 0.6rem; border:1px solid #374151;">${h || ''}</th>`).join('') + '</tr>';

            const displayRows = rows.slice(0, 5);
            tbody.innerHTML = displayRows.map(r => {
                return '<tr>' + headers.map((_, i) => `<td style="padding:0.35rem 0.6rem; border:1px solid #e5e7eb;">${r[i] || ''}</td>`).join('') + '</tr>';
            }).join('');

            document.getElementById('previewCountBadge').innerText = `${rows.length} records ready to import`;
            document.getElementById('excelPreviewArea').style.display = 'block';

            const confirmBtn = document.getElementById('btnConfirmImport');
            confirmBtn.disabled = false;
            confirmBtn.style.opacity = '1';
            confirmBtn.innerText = `Confirm & Import (${rows.length} Records)`;
        } catch(err) {
            console.error('Error reading Excel file:', err);
            alert('Failed to parse file: ' + err.message);
        }
    };

    reader.readAsBinaryString(file);
};

window.executeBulkImport = function() {
    const modal = document.getElementById('excelImportModal');
    const type = modal ? modal.dataset.importType : 'assets';
    const rows = window.parsedExcelRows || [];

    if (rows.length === 0) {
        alert('No records found to import.');
        return;
    }

    if (type === 'assets') {
        const table = document.getElementById('assetsMasterTable');
        const tbody = table ? table.querySelector('tbody') : null;
        rows.forEach(item => {
            const tag = item['Asset Tag'] || item['Tag'] || ('AST' + Math.floor(1000 + Math.random() * 9000));
            const name = item['Name / Model'] || item['Name'] || item['Model'] || 'Asset ' + tag;
            const cat = item['Category'] || 'General';
            const brand = item['Brand'] || 'Generic';
            const sn = item['Serial Number'] || item['Serial'] || ('SN-' + Math.floor(100000 + Math.random() * 900000));
            const assignee = item['Assigned To'] || 'Inventory Pool';
            const status = item['Status'] || 'Available';
            const condition = item['Condition'] || 'Good';
            const pDate = item['Purchase Date'] || new Date().toISOString().split('T')[0];
            const warranty = item['Warranty'] || '1 Year';

            if (tbody) {
                const tr = document.createElement('tr');
                tr.setAttribute('data-title', name);
                tr.setAttribute('data-tag', tag);
                tr.setAttribute('data-status', status);
                tr.innerHTML = `
                    <td><strong class="asset-tag">${tag}</strong></td>
                    <td><strong>${name}</strong></td>
                    <td><span class="category-pill">${cat}</span></td>
                    <td>${brand}</td>
                    <td><code class="serial-code">${sn}</code></td>
                    <td><strong>${assignee}</strong></td>
                    <td><span class="status-badge badge-available">${status}</span></td>
                    <td>${condition}</td>
                    <td>${pDate}</td>
                    <td>${warranty}</td>
                    <td><button class="action-btn" type="button" onclick="alert('Viewing asset: ${tag}')">View</button></td>
                `;
                tbody.insertBefore(tr, tbody.firstChild);
            }
        });
    } else if (type === 'employees') {
        const grid = document.getElementById('empCardsGrid');
        rows.forEach(item => {
            const empId = item['Employee ID'] || item['ID'] || ('EMP' + Math.floor(1000 + Math.random() * 9000));
            const name = item['Full Name'] || item['Name'] || 'Employee ' + empId;
            const status = item['Status'] || 'Active';
            const desig = item['Designation'] || 'Staff';
            const dept = item['Department'] || 'General';
            const branch = item['Branch'] || 'Main';
            const mobile = item['Mobile'] || '0000000000';
            const email = item['Email'] || (empId.toLowerCase() + '@company.com');
            const initials = name.split(' ').map(n => n[0]).join('').substring(0, 2).toUpperCase() || 'EM';

            if (grid) {
                const card = document.createElement('div');
                card.className = 'emp-profile-card emp-card-active';
                card.setAttribute('data-search', `${name} ${empId} ${dept} ${desig}`);
                card.innerHTML = `
                    <div>
                        <div class="emp-card-header">
                            <div class="emp-info-left">
                                <div class="emp-avatar-circle">${initials}</div>
                                <div>
                                    <div class="emp-name-title">${name}</div>
                                    <div class="emp-id-sub">${empId}</div>
                                </div>
                            </div>
                            <span class="status-text-pill status-active">${status}</span>
                        </div>
                        <div class="emp-meta-grid">
                            <span class="label">Designation</span><span class="val">${desig}</span>
                            <span class="label">Department</span><span class="val">${dept}</span>
                            <span class="label">Branch</span><span class="val">${branch}</span>
                            <span class="label">Mobile</span><span class="val">${mobile}</span>
                            <span class="label">Email</span><span class="val">${email}</span>
                        </div>
                    </div>
                    <div class="emp-card-actions">
                        <button class="emp-act-btn btn-emp-history" title="Work History">📋</button>
                        <button class="emp-act-btn btn-emp-edit" title="Edit">✏️</button>
                        <button class="emp-act-btn btn-emp-del" title="Delete" onclick="this.closest('.emp-profile-card').remove()">🗑️</button>
                    </div>
                `;
                grid.insertBefore(card, grid.firstChild);
            }
        });
    }

    closeExcelImportModal();
    alert(`✅ Bulk Import Successful! Imported ${rows.length} ${type} records into AssetOS.`);
};


document.addEventListener('DOMContentLoaded', () => {
    // Re-verify theme on DOM load
    const currentTheme = localStorage.getItem('assetos_accent_theme') || 'classic-charcoal';
    document.documentElement.setAttribute('data-theme', currentTheme);
    if (document.body) document.body.setAttribute('data-theme', currentTheme);
    // DOM Elements
    const menuToggleBtn = document.getElementById('menuToggleBtn');
    const sidebarCloseBtn = document.getElementById('sidebarCloseBtn');
    const sidebar = document.getElementById('sidebar');
    const sidebarBackdrop = document.getElementById('sidebarBackdrop');

    const addAssetBtn = document.getElementById('addAssetBtn');
    const modalOverlay = document.getElementById('modalOverlay');
    const modalCloseBtn = document.getElementById('modalCloseBtn');
    const modalCancelBtn = document.getElementById('modalCancelBtn');
    const addAssetForm = document.getElementById('addAssetForm');

    const topSearchInput = document.getElementById('topSearchInput');
    const tableSearchInput = document.getElementById('tableSearchInput');
    const activityTableBody = document.getElementById('activityTableBody');

    // -------------------------------------------------------------
    // Mobile Sidebar Drawer Toggle
    // -------------------------------------------------------------
    function openSidebar() {
        if (sidebar && sidebarBackdrop) {
            sidebar.classList.add('open');
            sidebarBackdrop.classList.add('active');
            document.body.style.overflow = 'hidden';
        }
    }

    function closeSidebar() {
        if (sidebar && sidebarBackdrop) {
            sidebar.classList.remove('open');
            sidebarBackdrop.classList.remove('active');
            document.body.style.overflow = '';
        }
    }

    if (menuToggleBtn) menuToggleBtn.addEventListener('click', openSidebar);
    if (sidebarCloseBtn) sidebarCloseBtn.addEventListener('click', closeSidebar);
    if (sidebarBackdrop) sidebarBackdrop.addEventListener('click', closeSidebar);

    // -------------------------------------------------------------
    // Modal Open & Close Handler
    // -------------------------------------------------------------
    function openModal() {
        if (modalOverlay) {
            modalOverlay.classList.add('open');
        }
    }

    function closeModal() {
        if (modalOverlay) {
            modalOverlay.classList.remove('open');
        }
    }

    if (addAssetBtn) addAssetBtn.addEventListener('click', openModal);
    if (modalCloseBtn) modalCloseBtn.addEventListener('click', closeModal);
    if (modalCancelBtn) modalCancelBtn.addEventListener('click', closeModal);

    // Close modal when clicking outside dialog
    if (modalOverlay) {
        modalOverlay.addEventListener('click', (e) => {
            if (e.target === modalOverlay) {
                closeModal();
            }
        });
    }

    // Close on Escape key
    document.addEventListener('keydown', (e) => {
        if (e.key === 'Escape') {
            closeModal();
            closeSidebar();
        }
    });

    // -------------------------------------------------------------
    // Live Search Filter for Recent Activity Table
    // -------------------------------------------------------------
    function filterTable(query) {
        const term = (query || '').toLowerCase().trim();
        const rows = activityTableBody ? activityTableBody.querySelectorAll('tr') : [];

        let visibleCount = 0;
        rows.forEach(row => {
            const text = row.textContent.toLowerCase();
            if (text.includes(term)) {
                row.style.display = '';
                visibleCount++;
            } else {
                row.style.display = 'none';
            }
        });

        // Update footer record counter if available
        const counterEl = document.getElementById('tableShowingCount');
        if (counterEl) {
            counterEl.textContent = `Showing ${visibleCount} of ${rows.length} assets`;
        }
    }

    if (topSearchInput) {
        topSearchInput.addEventListener('input', (e) => {
            const val = e.target.value;
            if (tableSearchInput) tableSearchInput.value = val;
            filterTable(val);
        });
    }

    if (tableSearchInput) {
        tableSearchInput.addEventListener('input', (e) => {
            const val = e.target.value;
            if (topSearchInput) topSearchInput.value = val;
            filterTable(val);
        });
    }

    // -------------------------------------------------------------
    // Form Submission (Simulated Add Asset)
    // -------------------------------------------------------------
    if (addAssetForm) {
        addAssetForm.addEventListener('submit', (e) => {
            e.preventDefault();

            const assetTag = document.getElementById('formAssetTag').value.trim();
            const assetName = document.getElementById('formAssetName').value.trim();
            const category = document.getElementById('formCategory').value;
            const assignee = document.getElementById('formAssignee').value.trim() || 'Unassigned';
            const department = document.getElementById('formDepartment').value.trim() || 'Inventory Pool';
            const status = document.getElementById('formStatus').value;

            // Determine badge class
            let badgeClass = 'badge-available';
            if (status.toLowerCase().includes('assigned')) badgeClass = 'badge-assigned';
            else if (status.toLowerCase().includes('maintenance')) badgeClass = 'badge-maintenance';

            // Create new row
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
                <td><span class="asset-tag">${escapeHtml(assetTag)}</span></td>
                <td><span class="asset-title">${escapeHtml(assetName)}</span></td>
                <td><span class="category-pill">${escapeHtml(category)}</span></td>
                <td>
                    <span class="assignee-name">${escapeHtml(assignee)}</span>
                    <span class="department-tag">${escapeHtml(department)}</span>
                </td>
                <td>
                    <span class="status-badge ${badgeClass}">
                        <span class="badge-dot"></span>
                        ${escapeHtml(status)}
                    </span>
                </td>
                <td>Just now</td>
                <td>
                    <button class="action-btn" type="button" onclick="alert('Viewing asset details for ${escapeHtml(assetTag)}')">View</button>
                </td>
            `;

            if (activityTableBody) {
                activityTableBody.insertBefore(newRow, activityTableBody.firstChild);
            }

            // Update stats counter dynamically on UI
            const totalStatEl = document.getElementById('statTotalAssets');
            if (totalStatEl) {
                const currentTotal = parseInt(totalStatEl.textContent.replace(/,/g, ''), 10) || 0;
                totalStatEl.textContent = (currentTotal + 1).toLocaleString();
            }

            // Reset form and close
            addAssetForm.reset();
            closeModal();

            // Refresh table counter
            filterTable(topSearchInput ? topSearchInput.value : '');
        });
    }

    function escapeHtml(text) {
        const div = document.createElement('div');
        div.textContent = text;
        return div.innerHTML;
    }
});
