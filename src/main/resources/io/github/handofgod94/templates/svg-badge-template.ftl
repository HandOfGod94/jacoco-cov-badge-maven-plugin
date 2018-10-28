<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
  width="${badge.getBadgeLabelWidth() + badge.getBadgeValueWidth()}" height="20">
  <linearGradient id="smooth" x2="0" y2="100%">
    <stop offset="0" stop-color="#bbb" stop-opacity=".1"/>
    <stop offset="1" stop-opacity=".1"/>
  </linearGradient>

  <mask id="round">
    <rect width="${badge.getBadgeLabelWidth() + badge.getBadgeValueWidth()}" height="20" rx="3" fill="#fff"/>
  </mask>

  <g mask="url(#round)">
    <rect width="${badge.getBadgeLabelWidth()}" height="20" fill="#555"/>
    <rect x="${badge.getBadgeLabelWidth()}" width="${badge.getBadgeValueWidth()}" height="20" fill="${badge.getResultColor()}"/>
    <rect width="${badge.getBadgeLabelWidth() + badge.getBadgeValueWidth()}" height="20" fill="url(#smooth)"/>
  </g>

  <g fill="#fff" text-anchor="middle" font-family="DejaVu Sans,Verdana,Geneva,sans-serif" font-size="11">
    <text x="${badge.getBadgeLabelWidth()/2}" y="15" fill="#010101" fill-opacity=".3">${badge.getBadgeLabel()}</text>
    <text x="${badge.getBadgeLabelWidth()/2}" y="14">${badge.getBadgeLabel()}</text>
    <text x="${badge.getBadgeLabelWidth()+badge.getBadgeValueWidth()/2-1}" y="15" fill="#010101" fill-opacity=".3">${badge.getBadgeValue()}%</text>
    <text x="${badge.getBadgeLabelWidth()+badge.getBadgeValueWidth()/2-1}" y="14">${badge.getBadgeValue()}%</text>
  </g>
</svg>
