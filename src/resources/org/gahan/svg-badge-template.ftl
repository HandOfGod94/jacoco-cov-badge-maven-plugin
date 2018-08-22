<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="${keyWidth + valueWidth}" height="20">
  <linearGradient id="smooth" x2="0" y2="100%">
    <stop offset="0" stop-color="#bbb" stop-opacity=".1"/>
    <stop offset="1" stop-opacity=".1"/>
  </linearGradient>

  <mask id="round">
    <rect width="${keyWidth + valueWidth}" height="20" rx="3" fill="#fff"/>
  </mask>

  <g mask="url(#round)">
    <rect width="${keyWidth}" height="20" fill="#555"/>
    <rect x="${keyWidth}" width="${valueWidth}" height="20" fill="#4c1"/>
    <rect width="${keyWidth + valueWidth}" height="20" fill="url(#smooth)"/>
  </g>

  <g fill="#fff" text-anchor="middle" font-family="DejaVu Sans,Verdana,Geneva,sans-serif" font-size="11">
    <text x="${keyWidth/2}" y="15" fill="#010101" fill-opacity=".3">${keyString}</text>
    <text x="${keyWidth/2}" y="14">${keyString}</text>
    <text x="${keyWidth+valueWidth/2-1}" y="15" fill="#010101" fill-opacity=".3">${valueString}</text>
    <text x="${keyWidth+valueWidth/2-1}" y="14">${valueString}</text>
  </g>
</svg>
