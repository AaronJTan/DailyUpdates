import Navbar from "@/components/Navbar"

export default function MainLayout({ children }) {
  return (
    <>
        <header>
          <Navbar />
        </header>

        <main className="flex-1">{children}</main>
    </>
  )
}
