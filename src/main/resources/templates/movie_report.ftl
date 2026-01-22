<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${directorName} - Movies</title>
    <style>
        @page {
            size: A4;
            margin: 1.5cm 2cm;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Georgia', 'Times New Roman', serif;
            background: white;
            color: #000;
            line-height: 1.5;
        }

        header {
            margin-bottom: 30px;
            padding-bottom: 12px;
            border-bottom: 3px solid #000;
        }

        h1 {
            font-size: 28px;
            font-weight: 700;
            color: #000;
            margin-bottom: 4px;
            letter-spacing: 0.5px;
            text-transform: uppercase;
        }

        h2 {
            font-size: 12px;
            font-weight: 400;
            color: #000;
            letter-spacing: 1.5px;
            text-transform: uppercase;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            page-break-inside: auto;
        }

        thead {
            border-bottom: 2px solid #000;
        }

        thead th {
            font-size: 11px;
            font-weight: 700;
            text-align: left;
            padding: 8px 4px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        thead th:first-child {
            width: 30px;
        }

        thead th:nth-child(2) {
            width: 70px;
        }

        thead th:last-child {
            width: 80px;
            text-align: right;
        }

        tbody tr {
            border-bottom: 1px solid #ddd;
            page-break-inside: avoid;
            page-break-after: auto;
        }

        tbody tr:last-child {
            border-bottom: 2px solid #000;
        }

        tbody td {
            padding: 12px 4px;
            vertical-align: middle;
        }

        .checkbox-cell {
            text-align: center;
        }

        .checkbox {
            display: inline-block;
            width: 18px;
            height: 18px;
            border: 1.5px solid #000;
            border-radius: 3px;
            background: white;
            -webkit-print-color-adjust: exact;
            print-color-adjust: exact;
        }

        .poster-cell {
            text-align: center;
        }

        .poster {
            width: 50px;
            height: 75px;
            object-fit: cover;
            border: 1px solid #ccc;
            display: block;
            margin: 0 auto;
        }

        .title-cell {
            font-size: 14px;
            font-weight: 600;
            color: #000;
        }

        .genre {
            display: block;
            font-size: 11px;
            font-weight: 400;
            color: #555;
            margin-top: 2px;
            font-style: italic;
        }

        .date-cell {
            text-align: right;
            font-size: 10px;
            font-weight: 500;
            font-variant-numeric: tabular-nums;
            font-family: 'Courier New', monospace;
        }

        .footer {
            margin-top: 30px;
            padding-top: 12px;
            border-top: 1px solid #ddd;
            font-size: 10px;
            color: #666;
            text-align: center;
        }

        @media print {
            body {
                margin: 0;
                padding: 0;
            }

            .no-print {
                display: none;
            }

            thead {
                display: table-header-group;
            }

            tbody tr {
                page-break-inside: avoid;
            }
        }
    </style>
</head>
<body>
    <header>
        <h1>${directorName}</h1>
        <h2>Director's Movies</h2>
    </header>

    <table>
        <thead>
            <tr>
                <th></th>
                <th>Poster</th>
                <th>Title</th>
                <th>Year</th>
            </tr>
        </thead>
        <tbody>
            <#list movies as movie>
            <tr>
                <td class="checkbox-cell">
                    <div class="checkbox"></div>
                </td>
                <td class="poster-cell">
                    <img src="${movie.poster}" alt="${movie.title}" class="poster">
                </td>
                <td class="title-cell">
                    ${movie.title}
                    <span class="genre">${movie.genre!""}</span>
                </td>
                <td class="date-cell">${movie.releaseDate}</td>
            </tr>
            </#list>
        </tbody>
    </table>

    <div class="footer">
        Generated on ${generatedOn}
    </div>

</body>
</html>